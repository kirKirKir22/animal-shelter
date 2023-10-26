package pro.sky.telegrambotanimalshelter.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotanimalshelter.models.Report;
import pro.sky.telegrambotanimalshelter.service.interfaces.ReportService;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Report create(@RequestBody Report report) {
        return reportService.add(report);
    }

    @GetMapping("/{id}")
    public Report read(@PathVariable long id) {
        return reportService.read(id);
    }

    @PutMapping("/{id}")
    public Report update(@PathVariable long id, @RequestBody Report report) {
        report.setId(id);
        return reportService.update(report);
    }

    @DeleteMapping("/{id}")
    public Report delete(@PathVariable long id) {
        return reportService.delete(id);
    }

    @GetMapping("/all")
    public List<Report> findAll() {
        return reportService.findAll();
    }
}