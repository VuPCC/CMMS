package cmms.service;

import cmms.entities.Asset;
import cmms.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }


    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }


    public List<Asset> getAssetsByName(String name) {
        return assetRepository.findByNameContainingIgnoreCase(name);
    }


    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status);
    }


    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }


    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
