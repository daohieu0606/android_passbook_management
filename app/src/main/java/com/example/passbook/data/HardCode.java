package com.example.passbook.data;

import com.example.passbook.data.entitys.Customer;
import com.example.passbook.data.entitys.DepositSlip;
import com.example.passbook.data.entitys.InfinitePassBook;
import com.example.passbook.data.entitys.PassBook;
import com.example.passbook.data.entitys.SixMonthPassBook;
import com.example.passbook.data.entitys.ThreeMonthPassBook;
import com.example.passbook.data.entitys.TransactionForm;
import com.example.passbook.data.entitys.WithdrawalSlip;
import com.example.passbook.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HardCode {
    public static class HardCodePassbook{
        public static List<PassBook> getPassBooks() {
            List<PassBook> result  = new ArrayList<>();

            ThreeMonthPassBook passBook1 = new ThreeMonthPassBook();
            ThreeMonthPassBook passBook2 = new ThreeMonthPassBook();
            ThreeMonthPassBook passBook7 = new ThreeMonthPassBook();
            ThreeMonthPassBook passBook8 = new ThreeMonthPassBook();
            ThreeMonthPassBook passBook9 = new ThreeMonthPassBook();
            SixMonthPassBook passBook3  = new SixMonthPassBook();
            SixMonthPassBook passBook4  = new SixMonthPassBook();
            InfinitePassBook passBook5 = new InfinitePassBook();
            InfinitePassBook passBook6 = new InfinitePassBook();

            passBook1.Id = 1;
            passBook2.Id = 2;
            passBook3.Id = 3;
            passBook4.Id = 4;
            passBook5.Id = 5;
            passBook6.Id = 6;
            passBook7.Id = 7;
            passBook8.Id = 8;
            passBook9.Id = 9;

            passBook1.customerId = 1;
            passBook2.customerId = 2;
            passBook3.customerId = 3;
            passBook4.customerId = 4;
            passBook5.customerId = 5;
            passBook6.customerId = 6;
            passBook7.customerId = 6;
            passBook8.customerId = 6;
            passBook9.customerId = 6;

            passBook1.creationDate = new Date();
            passBook2.creationDate = new Date();
            passBook3.creationDate = new Date();
            passBook4.creationDate = new Date();
            passBook5.creationDate = new Date();
            passBook6.creationDate = new Date();
            passBook7.creationDate = new Date();
            passBook8.creationDate = new Date();
            passBook9.creationDate = Utils.getNextDate(new Date());

            passBook1.amount = 1123;
            passBook2.amount = 421;
            passBook3.amount = 23213;
            passBook4.amount = 21231;
            passBook5.amount = 213;
            passBook6.amount = 1523;
            passBook6.amount = 1523;
            passBook6.amount = 1523;
            passBook6.amount = 1523;

            result.add(passBook1);
            result.add(passBook2);
            result.add(passBook3);
            result.add(passBook4);
            result.add(passBook5);
            result.add(passBook6);
            result.add(passBook7);
            result.add(passBook8);
            result.add(passBook9);

            return result;
        }
    }

    public static class HardCodeCustomer{
        public static List<Customer> getCustomers() {
            List<Customer> result = new ArrayList<>();

            Customer customer1 = new Customer();
            Customer customer2 = new Customer();
            Customer customer3 = new Customer();
            Customer customer4 = new Customer();
            Customer customer5 = new Customer();
            Customer customer6 = new Customer();

            customer1.Id = 1;
            customer2.Id = 2;
            customer3.Id = 3;
            customer4.Id = 4;
            customer5.Id = 5;
            customer6.Id = 6;

            customer1.fullName = "Shaquille Watt";
            customer2.fullName = "Arwel Pittman";
            customer3.fullName = "Cavan Clements";
            customer4.fullName = "Raja Bone";
            customer5.fullName = "Aditya Barnett";
            customer6.fullName = "Danni Swan";

            customer1.address = "4040  Hickory Street";
            customer2.address = "5595 Vernon Hall Rd";
            customer3.address = "321 Earl Slate Rd";
            customer4.address = "2582 Walworth Rd";
            customer5.address = "91 Bruce St";
            customer6.address = "4185 Poplar Corner Rd";

            customer1.identifyNumber = "711466083";
            customer2.identifyNumber = "394806492";
            customer3.identifyNumber = "713868735";
            customer4.identifyNumber = "969289032";
            customer5.identifyNumber = "638355264";
            customer6.identifyNumber = "452601592";

            result.add(customer1);
            result.add(customer2);
            result.add(customer3);
            result.add(customer4);
            result.add(customer5);
            result.add(customer6);

            return result;
        }
    }

    public static class HardCodeTransactionForm {
        public static List<TransactionForm> getTransactionForms() {
            List<TransactionForm> result = new ArrayList<>();

            TransactionForm transactionForm1 = new DepositSlip();
            TransactionForm transactionForm2 = new DepositSlip();
            TransactionForm transactionForm3 = new DepositSlip();
            TransactionForm transactionForm4 = new WithdrawalSlip();
            TransactionForm transactionForm5 = new WithdrawalSlip();
            TransactionForm transactionForm6 = new WithdrawalSlip();

            transactionForm1.Id = 1;
            transactionForm2.Id = 2;
            transactionForm3.Id = 3;
            transactionForm4.Id = 4;
            transactionForm5.Id = 5;
            transactionForm6.Id = 6;

            transactionForm1.passBookId = 1;
            transactionForm2.passBookId = 2;
            transactionForm3.passBookId = 3;
            transactionForm4.passBookId = 4;
            transactionForm5.passBookId = 5;
            transactionForm6.passBookId = 6;

            transactionForm1.customerId = 1;
            transactionForm2.customerId = 2;
            transactionForm3.customerId = 3;
            transactionForm4.customerId = 4;
            transactionForm5.customerId = 5;
            transactionForm6.customerId = 6;

            transactionForm1.transactionDateTime = new Date();
            transactionForm2.transactionDateTime = new Date();
            transactionForm3.transactionDateTime = new Date();
            transactionForm4.transactionDateTime = new Date();
            transactionForm5.transactionDateTime = new Date();
            transactionForm6.transactionDateTime = new Date();

            transactionForm1.amount = 1;
            transactionForm2.amount = 2;
            transactionForm3.amount = 3;
            transactionForm4.amount = 4;
            transactionForm5.amount = 5;
            transactionForm6.amount = 6;

            result.add(transactionForm1);
            result.add(transactionForm2);
            result.add(transactionForm3);
            result.add(transactionForm4);
            result.add(transactionForm5);
            result.add(transactionForm6);

            return result;
        }
    }
}
