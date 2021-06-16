create table FOO_Event (
	eventId LONG not null primary key,
	userId LONG,
	date_ DATE null,
	IPAddress VARCHAR(75) null,
	eventType VARCHAR(75) null,
	screenName VARCHAR(75) null
);