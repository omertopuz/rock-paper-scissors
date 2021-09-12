/**
 * CREATE Script for sample data
 */

-- Create 5 players

insert into player (id,username,total_score) values (1,'archer_well',0);
insert into player (id,username,total_score) values (2,'liberty_dragon',0);
insert into player (id,username,total_score) values (3,'friezer',0);
insert into player (id,username,total_score) values (4,'orange_glade',0);
insert into player (id,username,total_score) values (5,'scapula',0);
--
---- play some games for players
insert into game (id,player_id,player_move,computer_move,score) values (1,1,'PAPER','ROCK',1);
update player set total_score = 1 where id=1;
insert into game (id,player_id,player_move,computer_move,score) values (2,1,'PAPER','SCISSORS',-1);
update player set total_score = 0 where id=1;
insert into game (id,player_id,player_move,computer_move,score) values (3,1,'ROCK','SCISSORS',1);
update player set total_score = 1 where id=1;

insert into game (id,player_id,player_move,computer_move,score) values (4,3,'PAPER','ROCK',1);
update player set total_score = 1 where id=3;
insert into game (id,player_id,player_move,computer_move,score) values (5,3,'SCISSORS','SCISSORS',0);
insert into game (id,player_id,player_move,computer_move,score) values (6,3,'ROCK','SCISSORS',1);
update player set total_score = 2 where id=3;

