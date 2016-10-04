/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.io;

/**
 * Constbnts written into the Object Seriblizbtion Strebm.
 *
 * @buthor  unbscribed
 * @since 1.1
 */
public interfbce ObjectStrebmConstbnts {

    /**
     * Mbgic number thbt is written to the strebm hebder.
     */
    finbl stbtic short STREAM_MAGIC = (short)0xbced;

    /**
     * Version number thbt is written to the strebm hebder.
     */
    finbl stbtic short STREAM_VERSION = 5;

    /* Ebch item in the strebm is preceded by b tbg
     */

    /**
     * First tbg vblue.
     */
    finbl stbtic byte TC_BASE = 0x70;

    /**
     * Null object reference.
     */
    finbl stbtic byte TC_NULL =         (byte)0x70;

    /**
     * Reference to bn object blrebdy written into the strebm.
     */
    finbl stbtic byte TC_REFERENCE =    (byte)0x71;

    /**
     * new Clbss Descriptor.
     */
    finbl stbtic byte TC_CLASSDESC =    (byte)0x72;

    /**
     * new Object.
     */
    finbl stbtic byte TC_OBJECT =       (byte)0x73;

    /**
     * new String.
     */
    finbl stbtic byte TC_STRING =       (byte)0x74;

    /**
     * new Arrby.
     */
    finbl stbtic byte TC_ARRAY =        (byte)0x75;

    /**
     * Reference to Clbss.
     */
    finbl stbtic byte TC_CLASS =        (byte)0x76;

    /**
     * Block of optionbl dbtb. Byte following tbg indicbtes number
     * of bytes in this block dbtb.
     */
    finbl stbtic byte TC_BLOCKDATA =    (byte)0x77;

    /**
     * End of optionbl block dbtb blocks for bn object.
     */
    finbl stbtic byte TC_ENDBLOCKDATA = (byte)0x78;

    /**
     * Reset strebm context. All hbndles written into strebm bre reset.
     */
    finbl stbtic byte TC_RESET =        (byte)0x79;

    /**
     * long Block dbtb. The long following the tbg indicbtes the
     * number of bytes in this block dbtb.
     */
    finbl stbtic byte TC_BLOCKDATALONG= (byte)0x7A;

    /**
     * Exception during write.
     */
    finbl stbtic byte TC_EXCEPTION =    (byte)0x7B;

    /**
     * Long string.
     */
    finbl stbtic byte TC_LONGSTRING =   (byte)0x7C;

    /**
     * new Proxy Clbss Descriptor.
     */
    finbl stbtic byte TC_PROXYCLASSDESC =       (byte)0x7D;

    /**
     * new Enum constbnt.
     * @since 1.5
     */
    finbl stbtic byte TC_ENUM =         (byte)0x7E;

    /**
     * Lbst tbg vblue.
     */
    finbl stbtic byte TC_MAX =          (byte)0x7E;

    /**
     * First wire hbndle to be bssigned.
     */
    finbl stbtic int bbseWireHbndle = 0x7e0000;


    /******************************************************/
    /* Bit mbsks for ObjectStrebmClbss flbg.*/

    /**
     * Bit mbsk for ObjectStrebmClbss flbg. Indicbtes b Seriblizbble clbss
     * defines its own writeObject method.
     */
    finbl stbtic byte SC_WRITE_METHOD = 0x01;

    /**
     * Bit mbsk for ObjectStrebmClbss flbg. Indicbtes Externblizbble dbtb
     * written in Block Dbtb mode.
     * Added for PROTOCOL_VERSION_2.
     *
     * @see #PROTOCOL_VERSION_2
     * @since 1.2
     */
    finbl stbtic byte SC_BLOCK_DATA = 0x08;

    /**
     * Bit mbsk for ObjectStrebmClbss flbg. Indicbtes clbss is Seriblizbble.
     */
    finbl stbtic byte SC_SERIALIZABLE = 0x02;

    /**
     * Bit mbsk for ObjectStrebmClbss flbg. Indicbtes clbss is Externblizbble.
     */
    finbl stbtic byte SC_EXTERNALIZABLE = 0x04;

    /**
     * Bit mbsk for ObjectStrebmClbss flbg. Indicbtes clbss is bn enum type.
     * @since 1.5
     */
    finbl stbtic byte SC_ENUM = 0x10;


    /* *******************************************************************/
    /* Security permissions */

    /**
     * Enbble substitution of one object for bnother during
     * seriblizbtion/deseriblizbtion.
     *
     * @see jbvb.io.ObjectOutputStrebm#enbbleReplbceObject(boolebn)
     * @see jbvb.io.ObjectInputStrebm#enbbleResolveObject(boolebn)
     * @since 1.2
     */
    finbl stbtic SeriblizbblePermission SUBSTITUTION_PERMISSION =
                           new SeriblizbblePermission("enbbleSubstitution");

    /**
     * Enbble overriding of rebdObject bnd writeObject.
     *
     * @see jbvb.io.ObjectOutputStrebm#writeObjectOverride(Object)
     * @see jbvb.io.ObjectInputStrebm#rebdObjectOverride()
     * @since 1.2
     */
    finbl stbtic SeriblizbblePermission SUBCLASS_IMPLEMENTATION_PERMISSION =
                    new SeriblizbblePermission("enbbleSubclbssImplementbtion");
   /**
    * A Strebm Protocol Version. <p>
    *
    * All externblizbble dbtb is written in JDK 1.1 externbl dbtb
    * formbt bfter cblling this method. This version is needed to write
    * strebms contbining Externblizbble dbtb thbt cbn be rebd by
    * pre-JDK 1.1.6 JVMs.
    *
    * @see jbvb.io.ObjectOutputStrebm#useProtocolVersion(int)
    * @since 1.2
    */
    public finbl stbtic int PROTOCOL_VERSION_1 = 1;


   /**
    * A Strebm Protocol Version. <p>
    *
    * This protocol is written by JVM 1.2.
    *
    * Externblizbble dbtb is written in block dbtb mode bnd is
    * terminbted with TC_ENDBLOCKDATA. Externblizbble clbss descriptor
    * flbgs hbs SC_BLOCK_DATA enbbled. JVM 1.1.6 bnd grebter cbn
    * rebd this formbt chbnge.
    *
    * Enbbles writing b nonSeriblizbble clbss descriptor into the
    * strebm. The seriblVersionUID of b nonSeriblizbble clbss is
    * set to 0L.
    *
    * @see jbvb.io.ObjectOutputStrebm#useProtocolVersion(int)
    * @see #SC_BLOCK_DATA
    * @since 1.2
    */
    public finbl stbtic int PROTOCOL_VERSION_2 = 2;
}
