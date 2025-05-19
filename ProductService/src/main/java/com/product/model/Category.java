package com.product.model;

public enum Category {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    BOOKS("Books"),
    HOME_GOODS("Home Goods"),
    SPORTS("Sports"),
    GROCERY("Grocery");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category getCategory(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            return null;
        }
        for (Category category : Category.values()) {
            if (category.name().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
