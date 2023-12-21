package chronosacaria.mcdar.config;

public class EnchantmentIDConfigHelper {
    boolean isEnabled = true;
    boolean isAvailableForEnchantedBookOffer = true;
    boolean isAvailableForRandomSelection = true;
    int maxLevel = 3;

    public boolean mcdar$getIsEnabled() {
        return isEnabled;
    }
    public boolean mcdar$getIsAvailableForEnchantedBookOffer() {
        return isAvailableForEnchantedBookOffer;
    }
    public boolean mcdar$getIsAvailableForRandomSelection() {
        return isAvailableForRandomSelection;
    }
    public int mcdar$getMaxLevel() {
        return maxLevel;
    }

    @SuppressWarnings("unused")
    public EnchantmentIDConfigHelper(){
    }

    public EnchantmentIDConfigHelper(boolean isEnabled, boolean isAvailableForEnchantedBookOffer, boolean isAvailableForRandomSelection, int maxLevel) {
        this.isEnabled = isEnabled;
        this.isAvailableForEnchantedBookOffer = isAvailableForEnchantedBookOffer;
        this.isAvailableForRandomSelection = isAvailableForRandomSelection;
        this.maxLevel = maxLevel;
    }
}