package by.minsler.skarnik.dao;

import java.util.List;

import by.minsler.skarnik.beans.Article;
import by.minsler.skarnik.beans.Def;
import by.minsler.skarnik.beans.Key;

public interface ArticleKeyDefDAO {

	int addDef(Def def);

	Def getDef(int id);

	int addKey(Key key);

	Key getKey(int id);

	Key getKeyStrict(String text);

	List<Key> getKey(String text);

	List<Key> getKeyLimit(String text);

	int addArticle(Article article);

	Article getArticle(int id);

	Article getArticleByKeyId(int keyId);
}
