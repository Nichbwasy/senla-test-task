import com.senla.bank.repository.dao.BankRepository;
import com.senla.bank.repository.dao.implementations.BankRepositoryImpl;
import com.senla.bank.repository.entities.BankAccount;
import com.senla.bank.repository.entities.CreditCard;

public class debug {

    public static void main(String[] args) {

        BankRepository bankRepository = new BankRepositoryImpl();

        BankAccount account1 = new BankAccount("12345678901234567890", 100D);
        CreditCard card1 = new CreditCard("12345678901234567890", "1234-1234-1234-1234", "1234");
        CreditCard card2 = new CreditCard("12345678901234567890", "1444-1444-1444-1444", "1444");
        CreditCard card3 = new CreditCard("00000000000000000000", "0000-0000-0000-0000", "0000");

        //bankRepository.createAccount(account1);

        //bankRepository.createCreditCard(card1);
        //bankRepository.createCreditCard(card2);
        Double money = bankRepository.getMoney("1444-1444-1444-1444");
        bankRepository.setMoney("1444-1444-1444-1444", money + 150D);
        bankRepository.setMoney("1234-1234-1234-1234", money + 250D);
        bankRepository.blockCreditCard("1234-1234-1234-1234");
        bankRepository.unblockCreditCard("1234-1234-1234-1234");
        bankRepository.save();
        System.out.println();

    }


}
