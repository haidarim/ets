
# run the application: 
Using docker first you need to build the application image (the application will be placed in the image), if not already builded. 
The image file can be builded by the command below:

```bash
sudo docker -t er-to-sql-front .
```

run the application: 
```bash
sudo docker run --network host er-to-sql-front
```