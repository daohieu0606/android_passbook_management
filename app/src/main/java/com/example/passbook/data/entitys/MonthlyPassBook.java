package com.example.passbook.data.entitys;

public class MonthlyPassBook extends PassBook {
    public int nMonth;

    private MonthlyPassBook() {
    }

    private MonthlyPassBook(int nMonth){
        this.nMonth = nMonth;
    }

    public static MonthlyPassBook createInstance(int amount, int nMonth) {
        if(isOk(amount)) {
            return new MonthlyPassBook(nMonth);
        }
        return null;
    }

    private static boolean isOk(int amount) {
        return amount > 100000;
    }

    @Override
    public void deposit(int amount) {
        //do nothing
    }
}
