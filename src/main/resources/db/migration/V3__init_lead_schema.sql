-- Lead Schema
CREATE TABLE offices (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    phone VARCHAR(50) NOT NULL,
    region VARCHAR(20) NOT NULL, -- BAC, TRUNG, NAM
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    order_no INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

CREATE TABLE contact_persons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    title VARCHAR(150),
    email VARCHAR(150),
    phone VARCHAR(50),
    office_id UUID REFERENCES offices(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);

CREATE TABLE leads (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(150),
    company VARCHAR(255),
    service_id UUID REFERENCES logistics_services(id) ON DELETE SET NULL,
    service_name VARCHAR(255),
    message TEXT,
    status VARCHAR(50) DEFAULT 'NEW', -- NEW, CONTACTED, CLOSED
    source VARCHAR(100),
    ip_address VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100)
);
