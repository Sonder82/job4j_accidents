create TABLE if not exists accident_rules (
  id serial PRIMARY KEY,
  accident_id int NOT NULL REFERENCES accidents(id),
  rule_id int NOT NULL REFERENCES rules(id)
);