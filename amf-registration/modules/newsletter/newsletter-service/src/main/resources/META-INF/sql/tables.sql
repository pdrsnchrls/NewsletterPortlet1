create table Newsletter_Issue (
	issueId LONG not null primary key,
	issueNumber LONG,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	issueDate DATE null,
	byline VARCHAR(75) null
);

create table Newsletter_Newsletter (
	newsletterId LONG not null primary key,
	issueNumber LONG,
	title VARCHAR(75) null,
	author VARCHAR(75) null,
	order_ INTEGER,
	content TEXT null
);