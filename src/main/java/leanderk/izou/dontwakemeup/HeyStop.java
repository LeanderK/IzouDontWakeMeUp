package leanderk.izou.dontwakemeup;

import intellimate.izou.addon.PropertiesContainer;
import intellimate.izou.events.Event;
import intellimate.izou.events.EventsController;
import intellimate.izou.system.Context;

/**
 * @author LeanderK
 * @version 1.0
 */
public class HeyStop implements EventsController {
    public static final String ID = HeyStop.class.getCanonicalName();
    public static final String KEY_IGNORE_ALARM = "ignoreAlarm";
    public static final String KEY_SILENCE = "silence";

    private Context context;
    private PropertiesContainer propertiesContainer;

    public HeyStop(Context context, PropertiesContainer propertiesContainer) {
        this.context = context;
        this.propertiesContainer = propertiesContainer;
    }

    @Override
    public boolean controlEventDispatcher(Event event) {
        if (event.containsDescriptor("izou.alarm")) {
            String value = propertiesContainer.getProperties().getProperty(KEY_IGNORE_ALARM);
            //noinspection RedundantIfStatement
            if (value != null && value.equals("true")) {
                return false;
            } else {
                return true;
            }
        }

        return propertiesContainer.getProperties().stringPropertyNames().stream()
                .filter(key -> key.startsWith(KEY_SILENCE))
                .map(key -> propertiesContainer.getProperties().getProperty(key))
                .map(TimeChecker::new)
                .noneMatch(TimeChecker::matches);
    }

    @Override
    public void exceptionThrown(Exception e) {
        context.logger.getLogger().fatal("EventsController crashed", e);
    }

    @Override
    public String getID() {
        return ID;
    }
}
