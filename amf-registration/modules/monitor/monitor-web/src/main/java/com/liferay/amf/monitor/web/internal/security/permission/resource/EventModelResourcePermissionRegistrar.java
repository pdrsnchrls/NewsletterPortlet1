package com.liferay.amf.monitor.web.internal.security.permission.resource;

import com.liferay.amf.monitor.constants.MonitorConstants;
import com.liferay.amf.monitor.model.Event;
import com.liferay.amf.monitor.service.EventLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;

import java.util.Dictionary;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true)
public class EventModelResourcePermissionRegistrar {

	@Activate
	public void activate(BundleContext bundleContext) {
		Dictionary<String, Object> properties = new HashMapDictionary<>();

        properties.put("model.class.name", Event.class.getName());

        _serviceRegistration = bundleContext.registerService(
            ModelResourcePermission.class,
            ModelResourcePermissionFactory.create(
                BlogsEntry.class, BlogsEntry::getEntryId,
                _blogsEntryLocalService::getEntry, _portletResourcePermission,
                (modelResourcePermission, consumer) -> {
                    consumer.accept(
                        new StagedModelPermissionLogic<>(
                            _stagingPermission, BlogsPortletKeys.BLOGS,
                            BlogsEntry::getEntryId));
                    consumer.accept(
                        new WorkflowedModelPermissionLogic<>(
                            _workflowPermission, modelResourcePermission,
                            BlogsEntry::getEntryId));
                }),
            properties);
	}
	
    @Deactivate
    public void deactivate() {
        _serviceRegistration.unregister();
    }

    @Reference
    private EventLocalService _blogsEntryLocalService;

    @Reference(target = "(resource.name=" + MonitorConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission _portletResourcePermission;

    private ServiceRegistration<ModelResourcePermission> _serviceRegistration;

    @Reference
    private StagingPermission _stagingPermission;

    @Reference
    private WorkflowPermission _workflowPermission;
}
