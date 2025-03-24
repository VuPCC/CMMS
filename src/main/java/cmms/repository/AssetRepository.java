package cmms.repository;

import cmms.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {


    List<Asset> findByNameContainingIgnoreCase(String name);

    List<Asset> findByStatus(String status);

    List<Asset> findByVendorId(Long vendorId);

    @Query("SELECT a FROM Asset a WHERE a.purchaseDate BETWEEN :startDate AND :endDate")
    List<Asset> findAssetsByPurchaseDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

    Optional<Asset> findById(Long id);
}
