package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.RoomType;
import za.ac.cput.service.RoomTypeService;

@RestController
@RequestMapping("/roomtype")
public class RoomTypeController
{
    private RoomTypeService service;

    @Autowired
    RoomTypeController(RoomTypeService service)
    {
        this.service = service;
    }

    @PostMapping("/create")
    public RoomType create(@RequestBody RoomType roomType)
    {
        return service.create(roomType);
    }

    @GetMapping("/read/{id}")
    public RoomType read(@PathVariable Long id)
    {
        return service.read(id);
    }

    @PutMapping("/update")
    public RoomType update(@RequestBody RoomType roomType)
    {
        return service.update(roomType);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id)
    {
        RoomType roomType = service.read(id);
        if(roomType != null)
        {
            service.delete(roomType);
        }
    }
}
