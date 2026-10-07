-- V9: Add source_page to leads, add missing columns
ALTER TABLE leads
  ADD COLUMN IF NOT EXISTS source_page VARCHAR(255);

-- posts.read_minutes đã có từ V7, đảm bảo column tồn tại
ALTER TABLE posts
  ADD COLUMN IF NOT EXISTS read_minutes INT DEFAULT 5;
