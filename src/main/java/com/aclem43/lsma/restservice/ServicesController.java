package com.aclem43.lsma.restservice;

import com.sun.jna.platform.win32.W32Service;
import com.sun.jna.platform.win32.W32ServiceManager;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.Winsvc;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {
        W32ServiceManager serviceManager;
    private Object service;

    public ServicesController() {
        serviceManager = new W32ServiceManager(null,null);

    }

    @GetMapping("/")
    public Services services(){

        List<Service> services_list = new ArrayList<>();

        serviceManager.open(Winsvc.SERVICE_QUERY_STATUS);

        Winsvc.ENUM_SERVICE_STATUS_PROCESS[] services = serviceManager.enumServicesStatusExProcess(WinNT.SERVICE_WIN32, Winsvc.SERVICE_STATE_ALL	,null);
        List<Winsvc.ENUM_SERVICE_STATUS_PROCESS> service_list = Arrays.stream(services).toList();
        service_list.forEach(service -> {
            services_list.add(new Service(service.lpServiceName,service.lpDisplayName,service.ServiceStatusProcess.dwCurrentState));
        });
        serviceManager.close();

        return new Services(services_list);
    }

    @PostMapping("/action")
    public LiteService action(@RequestBody ServiceDTO dto){
        serviceManager.open(Winsvc.SC_MANAGER_ALL_ACCESS);
        W32Service service = serviceManager.openService(dto.getServiceName(), Winsvc.SC_MANAGER_ALL_ACCESS);
        switch (dto.getAction()){
            case START -> service.startService();
            case STOP -> service.stopService();
        }

        service.close();

        return new LiteService(dto.getServiceName(),3);
    }

}
