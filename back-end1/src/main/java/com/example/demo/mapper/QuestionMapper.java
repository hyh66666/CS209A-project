package com.example.demo.mapper;

import com.example.demo.enity.Owner;
import com.example.demo.enity.bag;
import com.example.demo.enity.question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Select("select * from \"question\"")
    public List<question> findAllQuestion();

    @Select("select * from \"question\" where id = #{id}")
    public question findQuestion(int id);
    @Select("select * from \"question\" where \"questionId\" = #{iii}")
    public question findQuestionByQuestionId(String iii);
    @Insert("INSERT INTO \"question\" (id, tags, \"isAnswered\", \"viewCount\", \"acceptedAnswerId\", \"answerCount\", score, " +
            "\"lastActivityDate\", \"creationDate\", \"questionId\", \"contentLicense\", link, title, \"userId\") VALUES (DEFAULT," +
            " #{tags}, #{isAnswered},#{viewCount},#{acceptedAnswerId},#{answerCount},#{score}, #{lastActivityDate}," +
            " #{creationDate}, #{questionId}, #{contentLicense},#{link}, #{title}, #{userId})")
    public int insert(question question);

    @Select("SELECT COUNT(*) FROM question WHERE \"isAnswered\" = 'true'")
    public int is_answer_count();

    @Select("SELECT  AVG(CAST(\"answerCount\" AS numeric)) FROM question;")
    public float Qyi2yi();
    @Select("SELECT  MAX(CAST(\"answerCount\" AS numeric)) FROM question;")
    public float Qyi2y();



    @Select("select \"answerCount\" , count(*) from question\n" +
            "    group by \"answerCount\"  order by CAST(\"answerCount\" AS numeric) asc;")
    public List<bag> Qyi3();

    @Select("  SELECT dis, COUNT(*) as ii\n" +
            "    FROM (\n" +
            "            SELECT unnest(string_to_array(translate(tags, '[]\"', ''), ',')) as dis\n" +
            "    FROM question\n" +
            "     ) as subquery\n" +
            "    GROUP BY dis order by ii desc\n" +
            "    limit 5;")
    public List<bag> Qsan1();

    @Select("select tags ,sum(CAST(score AS numeric)) as iii from\n" +
            "    (select tags,score from question order by score) as jj\n" +
            "GROUP BY tags order by iii desc  \n" +
            "limit 5;")
    public List<bag> Qsan2yi();

    @Select("SELECT dis, sum(CAST(score AS numeric)) as ii\n" +
            "FROM (\n" +
            "         SELECT unnest(string_to_array(translate(tags, '[]\"', ''), ',')) as dis, score\n" +
            "         FROM question\n" +
            "     ) as subquery\n" +
            "GROUP BY dis order by ii desc\n" +
            "limit 5;")
    public List<bag> Qsan2er();

    @Select("select tags ,sum(CAST(\"viewCount\" AS numeric)) as iii from\n" +
            "    (select tags,\"viewCount\" from question order by \"viewCount\") as jj\n" +
            "GROUP BY tags order by iii desc\n" +
            "limit 5;")
    public List<bag> Qsan3yi();

    @Select("SELECT dis, sum(CAST(\"viewCount\" AS numeric)) as ii\n" +
            "FROM (\n" +
            "         SELECT unnest(string_to_array(translate(tags, '[]\"', ''), ',')) as dis, \"viewCount\"\n" +
            "         FROM question\n" +
            "     ) as subquery\n" +
            "GROUP BY dis order by ii desc\n" +
            "limit 5;")
    public List<bag> Qsan3er();


    @Select("select differentUserCount, count(*) from\n" +
            "(SELECT AAA, COUNT(DISTINCT \"userId\")  AS differentUserCount\n" +
            "FROM (\n" +
            "         SELECT \"questionId\" as AAA, \"userId\" FROM question\n" +
            "         UNION ALL\n" +
            "         SELECT \"postId\" as AAA, \"userId\" FROM comment\n" +
            "         UNION ALL\n" +
            "         SELECT \"questionId\" as AAA, \"userId\" FROM answer\n" +
            "     ) AS combined\n" +
            "GROUP BY AAA\n" +
            "HAVING COUNT(DISTINCT \"userId\") > 1\n" +
            ") as BBB\n" +
            "GROUP BY differentUserCount;")
    public List<bag> Qsi1();

    @Select("select differentUserCount, count(*) from\n" +
            "    (SELECT AAA, COUNT(DISTINCT \"userId\")  AS differentUserCount\n" +
            "     FROM (\n" +
            "              SELECT \"questionId\" as AAA, \"userId\" FROM answer\n" +
            "          ) AS combined\n" +
            "     GROUP BY AAA\n" +
            "     HAVING COUNT(DISTINCT \"userId\") > 1\n" +
            "    ) as BBB\n" +
            "GROUP BY differentUserCount\n" +
            ";")
    public List<bag> Qsi2yi();

    @Select("select differentUserCount, count(*) from\n" +
            "    (SELECT AAA, COUNT(DISTINCT \"userId\")  AS differentUserCount\n" +
            "     FROM (\n" +
            "              SELECT \"postId\" as AAA, \"userId\" FROM comment\n" +
            "          ) AS combined\n" +
            "     GROUP BY AAA\n" +
            "     HAVING COUNT(DISTINCT \"userId\") > 1\n" +
            "    ) as BBB\n" +
            "GROUP BY differentUserCount;")
    public List<bag> Qsi2er();

    @Select("SELECT AAA, COUNT(*) AS occurrenceCount\n" +
            "FROM (\n" +
            "         SELECT \"userId\" as AAA FROM question\n" +
            "         UNION ALL\n" +
            "         SELECT \"userId\" as AAA FROM comment\n" +
            "         UNION ALL\n" +
            "         SELECT \"userId\" as AAA FROM answer\n" +
            "     ) AS combined\n" +
            "GROUP BY AAA\n" +
            "ORDER BY occurrenceCount DESC\n" +
            "LIMIT 5;")
    public List<bag> Qsi3();


    @Select("SELECT  AVG(CAST(\"answerCount\" AS numeric)),MAX(CAST(\"answerCount\" AS numeric)) FROM question;")
    public bag Qyi2();

    @Select("select * from question where \"answerCount\" = '0';")
    public List<question> Qyi1();



}
