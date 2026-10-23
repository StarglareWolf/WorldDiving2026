首先根据文档中给定的方法将相应赛事的运动员数据以及赛事数据 .json 文件下载下来

我采用了最笨的方式，通过 ctrl+F 对 athletes.json 中的嵌套关系进行排版，发现这样的关系
Country(Participations(运动员数据(参与的赛事)))
各分级情况如下：
Country : CountryName, CountryCode

Participations[] : Athlete

Athlete : ResultedId, Gender, PreferredFirstName, preferredLastName, Disciplines[]

按照这个关系，就开始建立类 Country(Athletes[]), Athlete(Discipilines[]), Disciplines[] 

通过 alt + insert 可以快速建立基本的 setter, getter, toString 等
