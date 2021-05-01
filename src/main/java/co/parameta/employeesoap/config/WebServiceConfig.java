package co.parameta.employeesoap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static co.parameta.employeesoap.endpoint.EmployeeEndpoint.NAMESPACE_URI;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext appContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(appContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/ws/*");
  }

  @Bean(name = "employee")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("EmployeePort");
    wsdl11Definition.setLocationUri("/ws/employee");
    wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
    wsdl11Definition.setSchema(schema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema employeeSchema() {
    return new SimpleXsdSchema(new ClassPathResource("/xsd/employee.xsd"));
  }
}
