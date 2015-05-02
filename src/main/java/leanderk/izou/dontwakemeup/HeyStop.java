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
    public static final String KEY_IGNORE_ALARM = "ignoreAlarm";
    public static final String KEY_SILENCE = "silence";

    public HeyStop(Context context) {
        super(context, ID);
    }

    /**
     * Controls whether the fired Event should be dispatched to all the listeners. This method should execute quickly
     *
     * @param eventModel the Event
     * @return true if it should dispatch, false if not
     */
    @Override
    public boolean controlEvents(EventModel eventModel) {
        if (eventModel.containsDescriptor(CommonEvents.ALARM_DESCRIPTOR)) {
            String value = getContext().getPropertiesAssistant().getProperties().getProperty(KEY_IGNORE_ALARM);
            //noinspection RedundantIfStatement
            if (value != null && value.equals("true")) {
                return false;
            } else {
                return true;
            }
        }

        return getContext().getPropertiesAssistant().getProperties().stringPropertyNames().stream()
                .filter(key -> key.startsWith(KEY_SILENCE))
                .map(key -> getContext().getPropertiesAssistant().getProperties().getProperty(key))
                .map(TimeChecker::new)
                .noneMatch(TimeChecker::matches);
    }
}
