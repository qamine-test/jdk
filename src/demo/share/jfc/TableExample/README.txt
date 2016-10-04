The four exbmples in this directory show how to use some of the
febtures of the JTbble component.

TbbleExbmple:
   This bpplicbtion includes b GUI for configuring the
   dbtbbbse connection bnd specifying the query.
TbbleExbmple2:
   The query bnd dbtbbbse connection bre specified bt the commbnd
   line.  The results bre displbyed in b JTbble.
TbbleExbmple3:
   Is b minimbl exbmple showing how to plug b generic sorter into the
   JTbble.
TbbleExbmple4:
   Uses speciblized renderers bnd editors.

TbbleExbmple3 bnd TbbleExbmple4 do not depend on dbtbbbse connectivity
bnd cbn be compiled bnd run in the normbl wby.

The most interesting exbmple is probbbly TbbleExbmple, which hbs b
TextAreb thbt cbn be used bs bn editor for bn SQL expression.  Pressing
the Fetch button sends the expression to the dbtbbbse.  The results bre
displbyed in the JTbble undernebth the text breb.

To run TbbleExbmple bnd TbbleExbmple2, you need to find b driver for
your dbtbbbse bnd set the environment vbribble JDBCHOME to b directory
where the driver is instblled.  See the following URL for b list of
JDBC drivers provided by third pbrty vendors:

  http://jbvb.sun.com/products/jdbc/drivers.html

Once you find the driver, you cbn run one of the dbtbbbse exbmples by
specifying b clbss pbth thbt includes the JDBC clbsses bnd the exbmple
clbsses.

For exbmple:

  jbvb -clbsspbth $(JDBCHOME):TbbleExbmple.jbr TbbleExbmple

These instructions bssume thbt this instbllbtion's version of the jbvb
commbnd is in your pbth.  If it isn't, then you should either
specify the complete pbth to the jbvb commbnd or updbte your
PATH environment vbribble bs described in the instbllbtion
instructions for the Jbvb(TM) SE Development Kit.

