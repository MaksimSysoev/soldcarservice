/*
create table if not exists sold_car_service as
select
    id,
    (random() * 18 + 1)::int as car_model_id,
    md5(random()::text)::char(17) as car_vin_code,
    'Buyer' || ((random() * 63 + 1)::int) as buyer_name,
    CAST(9001112233 + floor(random() * 9998887766) AS bigint) as buyer_phone,
    case
        when id % 3 = 0 then ('2017-01-03'::date - interval '2 day' + interval '12 month' * random())::date
        when id % 3 = 1 then ('2019-01-03'::date - interval '2 day' + interval '12 month' * random())::date
        when id % 3 = 2 then ('2020-01-03'::date - interval '2 day' + interval '12 month' * random())::date
        end as purchase_datetime
from generate_series(1,100000) as id
order by purchase_datetime;

alter table sold_car_service add primary key (id);
alter table sold_car_service add service_state varchar(255) default 'NEW';
alter table sold_car_service ADD FOREIGN KEY (car_model_id) references products(id) ON DELETE CASCADE;
*/

CREATE TABLE IF NOT EXISTS error_log
(
	id serial primary key,
	wrong_date date not null,
	message text not null
);
