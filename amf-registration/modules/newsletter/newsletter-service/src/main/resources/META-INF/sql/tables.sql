create table Newsletter_Issue (
	issueId LONG not null primary key,
	issueNumber INTEGER,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	issueDate DATE null
);

create table Newsletter_Newsletter (
	newsletterId LONG not null primary key,
	issueId LONG,
	title VARCHAR(75) null,
	author VARCHAR(75) null,
	order_ INTEGER,
	content VARCHAR(75) null
);