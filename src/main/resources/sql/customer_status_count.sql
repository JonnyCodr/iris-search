select customer, status, count(*)
from (select
        customer,
        patient_id,
        case
             when job_status_ref_id = 1 then 'AVAILABLE'
             when job_status_ref_id = 2 then 'WORKING'
             when job_status_ref_id = 3 then 'COMPLETE'
             when job_status_ref_id = 4 then 'FAILURE'
         end status
from iris_job.job job
inner join iris_job.job_run jr on job.job_id = jr.job_id
where job.customer = :customer_short_name
  and create_timestamp between :start_date::date and :end_date::date + 1
     ) qq
group by customer, status
order by status
