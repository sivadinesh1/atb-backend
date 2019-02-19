INSERT INTO article (id, language, title, r_title, description, read_time, body, category, tags, cover_img, article_img, status, article_bitly, created_by, published_time) 
  SELECT 1,'ad','a','sdf','sdf','fs','sd','s','sd','sd','sdf','sd','sfd','8',NULL from dual 
WHERE NOT EXISTS 
  (SELECT id FROM article WHERE id='1');
  