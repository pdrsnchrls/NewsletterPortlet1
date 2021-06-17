create table Monitor_Event (
	eventId LONG not null primary key,
	userId LONG,
	screenName VARCHAR(75) null,
	date_ DATE null,
	ipAddress VARCHAR(75) null,
	eventType VARCHAR(75) null
);