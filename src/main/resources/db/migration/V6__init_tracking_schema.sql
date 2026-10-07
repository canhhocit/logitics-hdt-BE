-- Tracking Schema
CREATE TABLE shipments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tracking_code VARCHAR(100) NOT NULL UNIQUE,
    service_name VARCHAR(255) NOT NULL,
    weight_info VARCHAR(255),
    origin VARCHAR(255),
    destination VARCHAR(255),
    eta_date VARCHAR(100),
    status VARCHAR(50) DEFAULT 'PENDING', -- PENDING, TRANSIT, DELIVERED
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

CREATE TABLE shipment_events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    shipment_id UUID NOT NULL REFERENCES shipments(id) ON DELETE CASCADE,
    event_time VARCHAR(100) NOT NULL, -- e.g. "29/09 18:30"
    location VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    order_no INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

-- Seed Admin Role and User
INSERT INTO roles (code, name, description) VALUES ('ADMIN', 'Administrator', 'System Administrator') ON CONFLICT (code) DO NOTHING;
-- Password is 'admin'
INSERT INTO users (username, password_hash, full_name, is_active, role_codes) VALUES ('admin', '$2a$10$fR8GBo1WOjeSPaose2j5IOADPInU9MDioGeFA5waSkSEZmnmkWQuC', 'System Admin', true, 'ADMIN');

