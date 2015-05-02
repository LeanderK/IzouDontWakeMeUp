package leanderk.izou.dontwakemeup;

import org.intellimate.izou.sdk.activator.Activator;
import org.intellimate.izou.sdk.addon.AddOn;
import org.intellimate.izou.sdk.contentgenerator.ContentGenerator;
import org.intellimate.izou.sdk.events.EventsController;
import org.intellimate.izou.sdk.output.OutputExtension;
import org.intellimate.izou.sdk.output.OutputPlugin;
import ro.fortsoft.pf4j.Extension;

/**
 * @author LeanderK
 * @version 1.0
 */
@Extension
public class DontWakeMeUp extends AddOn {
    public static final String ID = DontWakeMeUp.class.getCanonicalName();

    public DontWakeMeUp() {
        super(ID);
    }

    @Override
    public void prepare() {

    }

    @Override
    public Activator[] registerActivator() {
        return new Activator[0];
    }

    @Override
    public ContentGenerator[] registerContentGenerator() {
        return new ContentGenerator[0];
    }

    @Override
    public EventsController[] registerEventController() {
        EventsController[] eventsControllers = new EventsController[1];
        eventsControllers[0] = new HeyStop(getContext());
        return eventsControllers;
    }

    @Override
    public OutputPlugin[] registerOutputPlugin() {
        return new OutputPlugin[0];
    }

    @Override
    public OutputExtension[] registerOutputExtension() {
        return new OutputExtension[0];
    }
}
