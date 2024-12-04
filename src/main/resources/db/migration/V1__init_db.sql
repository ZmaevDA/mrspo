CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create the companies table
CREATE TABLE companies
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name     VARCHAR(255),
    industry VARCHAR(255)
);

-- Create the contacts table
CREATE TABLE contacts
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255),
    email      VARCHAR(255),
    phone      VARCHAR(255),
    company_id UUID,
    CONSTRAINT fk_company
        FOREIGN KEY (company_id)
            REFERENCES companies (id)
            ON DELETE SET NULL
);

-- Create the addresses table
CREATE TABLE addresses
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    street      VARCHAR(255),
    city        VARCHAR(255),
    state       VARCHAR(255),
    postal_code VARCHAR(20),
    country     VARCHAR(100),
    contact_id  UUID,
    CONSTRAINT fk_contact
        FOREIGN KEY (contact_id)
            REFERENCES contacts (id)
            ON DELETE CASCADE
);

-- Add indexes for faster lookups (optional)
CREATE INDEX idx_contact_company ON contacts (company_id);
CREATE INDEX idx_address_contact ON addresses (contact_id);
