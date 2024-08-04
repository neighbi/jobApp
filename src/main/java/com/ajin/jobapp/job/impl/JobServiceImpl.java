package com.ajin.jobapp.job.impl;

import com.ajin.jobapp.job.Job;
import com.ajin.jobapp.job.JobRepository;
import com.ajin.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<Job>();

    private JobRepository jobRepository;

//    private Long nextId = 101L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {

        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        Optional<Job> opt=jobRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        else
            return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {

       Optional<Job> opt=jobRepository.findById(id);
        if(opt.isPresent()){

            Job job=opt.get();
//          jobRepository.
               job.setTitle(updateJob.getTitle());
               job.setDescription(updateJob.getDescription());
               job.setMinSalary(updateJob.getMinSalary());
               job.setMaxSalary(updateJob.getMaxSalary());
               job.setLocation(updateJob.getLocation());
               jobRepository.save(job);
               return true;

       }
        return false;
    }


}
