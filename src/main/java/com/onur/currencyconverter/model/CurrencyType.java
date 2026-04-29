package com.onur.currencyconverter.model;

public enum CurrencyType {
        EUR("Euro", "€", "euflag.png"),
        USD("US Dollar", "$", "usaflag.png"),
        TRY("Turkish Lira", "₺", "turkishflag.png");

        private final String fullName;
        private final String symbol;
        private final String flagName;

        CurrencyType(String fullName, String symbol, String flagName) {
            this.fullName = fullName;
            this.symbol = symbol;
            this.flagName = flagName;
        }

        public String getFlagPath() {
            return "/images/" + flagName;
        }

        public String getFullName() {
            return fullName;
        }
        public String getSymbol() {
            return symbol;
        }
        public String getFlagName() {
            return flagName;
        }
}
