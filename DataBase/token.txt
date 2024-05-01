CREATE TABLE token(
	token_id Serial PRIMARY KEY,
	user_id serial,
	token Varchar(200) NOT NULL,
	token_expiration_date TIMESTAMP NOT NULL
)

CREATE OR REPLACE FUNCTION delete_expired_records()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM token WHERE token_expiration_date < CURRENT_TIMESTAMP;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_expired_trigger
AFTER INSERT OR UPDATE ON token
FOR EACH ROW EXECUTE FUNCTION delete_expired_records();