package by.minsler.skarnik.dao;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Card;

public interface CardDAO {

	ArrayList<Card> getCards();

	Card getCard(int id);

	boolean addCard(Card card);

	int deleteCard(int id);

	int deleteAllCard();
}
