package com.tsa.spring.payroll.specification;



import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.tsa.spring.payroll.dto.SearchData;


@Component
public class ReportClientSpecification {

    public static <T> Specification<T> searchReportClient(SearchData searchData) {
         return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (searchData.getSearchDivisi() != null && !searchData.getSearchDivisi().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("division"), searchData.getSearchDivisi()));
            }

            if (searchData.getSearchUnit() != null && !searchData.getSearchUnit().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("unitname"), "%" + searchData.getSearchUnit() + "%"));
            }

            if (searchData.getSearchPosisi() != null && !searchData.getSearchPosisi().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("position"), "%" + searchData.getSearchPosisi() + "%"));
            }

            if (searchData.getSearchBranch() != null && !searchData.getSearchBranch().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("branch"), "%" + searchData.getSearchBranch() + "%"));
            }

            if (searchData.getSearchBulan() != null && !searchData.getSearchBulan().isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("monthpayroll"), "%" + searchData.getSearchBulan() + "%"));
            }

            if (searchData.getSearchTahun() != null && !searchData.getSearchTahun().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("yearpayroll"), searchData.getSearchTahun()));
            }

            if (searchData.getSearchEmployeeType() != null && !searchData.getSearchEmployeeType().isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("employeeType"), searchData.getSearchEmployeeType()));
            }

            if (searchData.getSearchKeyword() != null && !searchData.getSearchKeyword().isEmpty()) {
                String keyword = "%" + searchData.getSearchKeyword() + "%";
                Predicate keywordPredicate = cb.or(
                        cb.like(root.get("nama"), keyword),
                        cb.like(root.get("nik"), keyword),
                        cb.like(root.get("employeeType"), keyword),
                        cb.like(root.get("division"), keyword),
                        cb.like(root.get("unitname"), keyword),
                        cb.like(root.get("branch"), keyword)
                );
                predicate = cb.and(predicate, keywordPredicate);
            }

            if (searchData.getSearchPeriodeStart() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("periodeStart"), searchData.getSearchPeriodeStart()));
            }
            
            if (searchData.getSearchPeriodeEnd() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("periodeEnd"), searchData.getSearchPeriodeEnd()));
            }
            
            return predicate;
        };
    }
}
