package leanderk.izou.dontwakemeup;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.AddOn;
import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.EventsController;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.output.OutputPlugin;

/**
 * @author LeanderK
 * @version 1.0
 */
public class DontWakeMeUp extends AddOn{
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
        return new EventsController[0];
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
