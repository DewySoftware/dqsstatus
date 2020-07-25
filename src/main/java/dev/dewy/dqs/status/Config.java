package dev.dewy.dqs.status;

public class Config
{
    private boolean donePostLoad = false;

    public Config doPostLoad()
    {
        if (this.donePostLoad)
        {
            throw new IllegalStateException("Config postload already done!");
        }

        this.donePostLoad = true;

        return this;
    }

    public Status status = new Status();

    public static final class Status
    {
        public String token = "bleh";
        public String ownerId = "326039530971070474";
    }
}
