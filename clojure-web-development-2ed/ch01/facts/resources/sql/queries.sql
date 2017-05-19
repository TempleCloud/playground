-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO user
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- :name update-user! :! :n
-- :doc update an existing user record
UPDATE user
SET first_name = :first_name, last_name = :last_name, email = :email
WHERE id = :id

-- :name get-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM user
WHERE id = :id

-- :name delete-user! :! :n
-- :doc delete a user given the id
DELETE FROM user
WHERE id = :id


-- :name save-fact! :! :n
-- :doc creates a new fact using the user_id, side_1, side_2, and timestamp keys
INSERT INTO fact
(user_id, side_1, side_2, timestamp)
VALUES (:user_id, :side_1, :side_2, :timestamp)
-- :name get-facts :? :*
-- :doc selects all available facts
SELECT * from fact

-- :name delete-fact! :! :n
-- :doc delete a fact given the id
DELETE FROM fact
WHERE id = :id
