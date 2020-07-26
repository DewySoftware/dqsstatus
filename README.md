# DQS Status

[Tired of the quarantine queue? Skip the wait with CTQ by clicking here!](https://discord.gg/pornhub)

This simple Discord bot indicates what state the CTQ service is in.

- **Online (Green)**: Normal, no issues
- **Idle (Orange)**: Planned Outage
- **Do Not Disturb (Red)**: Unplanned Outage
- **Invisible**: Something's Up Chief

I decided to open source it cos why tf not ¯\\_(ツ)_/¯

## Usage

In order to use this bot, create a `config.json` in its working directory before running, and fill it out like so:

```json
{
  "status": {
    "token": "THE_TOKEN",
    "ownerId": "YOUR_DISCORD_ID"
  }
}
```

### Commands

The two commands this bot has are `&set` and `&kill`.

- `&set`: Sets the bot's status.
- `&kill`: Shuts the bot down.
