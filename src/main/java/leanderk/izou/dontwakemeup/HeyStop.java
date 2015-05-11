package leanderk.izou.dontwakemeup;

import org.intellimate.izou.events.EventModel;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.events.CommonEvents;
import org.intellimate.izou.sdk.events.EventsController;

/**
 * @author LeanderK
 * @version 1.0
 */
public class HeyStop extends EventsController {
    public static final String ID = HeyStop.class.getCanonicalName();
    public static final String KEY_SILENCE = "silence";

    public HeyStop(Context context) {
        super(context, ID);
    }

    @Override
    public boolean controlEventDispatcher(EventModel eventModel) {
        boolean control = getContext().getPropertiesAssistant().getProperties().stringPropertyNames().stream()
                .filter(key -> key.startsWith(KEY_SILENCE))
                .map(key -> getContext().getPropertiesAssistant().getProperties().getProperty(key))
                .map(TimeChecker::new)
                .noneMatch(TimeChecker::matches);
        if (!control)
            return true;
        if (eventModel.containsDescriptor(CommonEvents.Response.FULL_RESPONSE_DESCRIPTOR))
            eventModel.removeDescriptor(CommonEvents.Response.FULL_RESPONSE_DESCRIPTOR);
        if (eventModel.containsDescriptor(CommonEvents.Response.MAJOR_RESPONSE_DESCRIPTOR))
            eventModel.removeDescriptor(CommonEvents.Response.MAJOR_RESPONSE_DESCRIPTOR);
        if (eventModel.containsDescriptor(CommonEvents.Response.MINOR_RESPONSE_DESCRIPTOR))
            eventModel.removeDescriptor(CommonEvents.Response.MINOR_RESPONSE_DESCRIPTOR);
        if (eventModel.containsDescriptor(CommonEvents.ALARM_DESCRIPTOR))
            return true;
        if (!eventModel.containsDescriptor(CommonEvents.Descriptors.NOT_INTERRUPT) ||
                !eventModel.containsDescriptor(CommonEvents.Descriptors.STOP_DESCRIPTOR))
            return false;
        return true;
    }

    /**
     * Controls whether the fired Event should be dispatched to all the listeners. This method should execute quickly
     *
     * @param eventModel the Event
     * @return true if it should dispatch, false if not
     */
    @Override
    public boolean controlEvents(EventModel eventModel) {
        return true;
    }
}
