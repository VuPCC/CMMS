package cmms.controller;

import cmms.entities.Asset;
import cmms.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    // Get ID
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        Optional<Asset> asset = assetService.getAssetById(id);
        return asset.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find by name
    @GetMapping("/search")
    public ResponseEntity<List<Asset>> getAssetsByName(@RequestParam String name) {
        List<Asset> assets = assetService.getAssetsByName(name);
        return ResponseEntity.ok(assets);
    }

    // Create
    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset newAsset = assetService.saveAsset(asset);
        return ResponseEntity.ok(newAsset);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        if (!assetService.getAssetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        asset.setId(id);
        Asset updatedAsset = assetService.saveAsset(asset);
        return ResponseEntity.ok(updatedAsset);
    }

    // Delete ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        if (!assetService.getAssetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}