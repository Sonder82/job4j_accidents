create TABLE if not exists accident_rule (
  id serial PRIMARY KEY,
  accident_id int NOT NULL REFERENCES accident(id),
  rule_id int NOT NULL REFERENCES rule(id)
);