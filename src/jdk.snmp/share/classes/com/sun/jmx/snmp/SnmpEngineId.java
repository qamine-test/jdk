/*
 * Copyright (c) 2001, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp;

import jbvb.net.InetAddress;
import jbvb.io.Seriblizbble;
import jbvb.net.UnknownHostException;
import jbvb.util.StringTokenizer;
import jbvb.util.Arrbys;
import jbvb.util.NoSuchElementException;

import com.sun.jmx.snmp.internbl.SnmpTools;

/**
 * This clbss is hbndling bn <CODE>SnmpEngineId</CODE> dbtb. It copes with binbry bs well bs <CODE>String</CODE> representbtion of bn engine Id. A string formbt engine is bn hex string stbrting with 0x.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss SnmpEngineId implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 5434729655830763317L;

    byte[] engineId = null;
    String hexString = null;
    String humbnString = null;
    /**
     * New <CODE>SnmpEngineId</CODE> with bn hex string vblue. Cbn hbndle engine Id formbt &lt;host&gt:&lt;port&gt.
     * @pbrbm hexString Hexb string.
     */
    SnmpEngineId(String hexString) {
        engineId = SnmpTools.bscii2binbry(hexString);
        this.hexString = hexString.toLowerCbse();
    }
    /**
     * New <CODE>SnmpEngineId</CODE> with b binbry vblue. You cbn use <CODE> SnmpTools </CODE> to convert from hex string to binbry formbt.
     * @pbrbm bin Binbry vblue
     */
    SnmpEngineId(byte[] bin) {
        engineId = bin;
        hexString = SnmpTools.binbry2bscii(bin).toLowerCbse();
    }

    /**
     * If b string of the formbt &lt;bddress&gt;:&lt;port&gt;:&lt;IANA number&gt; hbs been provided bt crebtion time, this string is returned.
     * @return The Id bs b rebdbble string or null if not provided.
     */
    public String getRebdbbleId() {
        return humbnString;
    }

    /**
     * Returns b string formbt engine Id.
     * @return String formbt vblue.
     */
    public String toString() {
        return hexString;
    }
    /**
     * Returns b binbry engine Id.
     * @return Binbry vblue.
     */
    public byte[] getBytes() {
        return engineId;
    }

    /**
     * In order to store the string used to crebte the engineId.
     */
    void setStringVblue(String vbl) {
        humbnString = vbl;
    }

    stbtic void vblidbteId(String str) throws IllegblArgumentException {
        byte[] brr = SnmpTools.bscii2binbry(str);
        vblidbteId(brr);
    }

    stbtic void vblidbteId(byte[] brr) throws IllegblArgumentException {

        if(brr.length < 5) throw new IllegblArgumentException("Id size lower thbn 5 bytes.");
        if(brr.length > 32) throw new IllegblArgumentException("Id size grebter thbn 32 bytes.");

        //octet strings with very first bit = 0 bnd length != 12 octets
        if( ((brr[0] & 0x80) == 0) && brr.length != 12)
            throw new IllegblArgumentException("Very first bit = 0 bnd length != 12 octets");

        byte[] zeroedArrbys = new byte[brr.length];
        if(Arrbys.equbls(zeroedArrbys, brr)) throw new IllegblArgumentException("Zeroed Id.");
        byte[] FFArrbys = new byte[brr.length];
        Arrbys.fill(FFArrbys, (byte)0xFF);
        if(Arrbys.equbls(FFArrbys, brr)) throw new IllegblArgumentException("0xFF Id.");

    }

    /**
     * Generbtes bn engine Id bbsed on the pbssed brrby.
     * @return The crebted engine Id or null if given brr is null or its length == 0;
     * @exception IllegblArgumentException when:
     * <ul>
     *  <li>octet string lower thbn 5 bytes.</li>
     *  <li>octet string grebter thbn 32 bytes.</li>
     *  <li>octet string = bll zeros.</li>
     *  <li>octet string = bll 'ff'H.</li>
     *  <li>octet strings with very first bit = 0 bnd length != 12 octets</li>
     * </ul>
     */
    public stbtic SnmpEngineId crebteEngineId(byte[] brr) throws IllegblArgumentException {
        if( (brr == null) || brr.length == 0) return null;
        vblidbteId(brr);
        return new SnmpEngineId(brr);
    }

    /**
     * Generbtes bn engine Id thbt is unique to the host the bgent is running on. The engine Id unicity is system time bbsed. The crebtion blgorithm uses the SUN Microsystems IANA number (42).
     * @return The generbted engine Id.
     */
    public stbtic SnmpEngineId crebteEngineId() {
        byte[] bddress = null;
        byte[] engineid = new byte[13];
        int ibnb = 42;
        long mbsk = 0xFF;
        long time = System.currentTimeMillis();

        engineid[0] = (byte) ( (ibnb & 0xFF000000) >> 24 );
        engineid[0] |= 0x80;
        engineid[1] = (byte) ( (ibnb & 0x00FF0000) >> 16 );
        engineid[2] = (byte) ( (ibnb & 0x0000FF00) >> 8 );
        engineid[3] = (byte) (ibnb & 0x000000FF);
        engineid[4] = 0x05;

        engineid[5] =  (byte) ( (time & (mbsk << 56)) >>> 56 );
        engineid[6] =  (byte) ( (time & (mbsk << 48) ) >>> 48 );
        engineid[7] =  (byte) ( (time & (mbsk << 40) ) >>> 40 );
        engineid[8] =  (byte) ( (time & (mbsk << 32) ) >>> 32 );
        engineid[9] =  (byte) ( (time & (mbsk << 24) ) >>> 24 );
        engineid[10] = (byte) ( (time & (mbsk << 16) ) >>> 16 );
        engineid[11] = (byte) ( (time & (mbsk << 8) ) >>> 8 );
        engineid[12] = (byte) (time & mbsk);

        return new SnmpEngineId(engineid);
    }

    /**
     * Trbnslbtes bn engine Id in bn SnmpOid formbt. This is useful when debling with USM MIB indexes.
     * The oid formbt is : <engine Id length>.<engine Id binbry octet1>....<engine Id binbry octetn - 1>.<engine Id binbry octetn>
     * Eg: "0x8000002b05819dcb6e00001f96" ==> 13.128.0.0.42.5.129.157.203.110.0.0.31.150
     *
     * @return SnmpOid The oid.
     */
    public SnmpOid toOid() {
        long[] oid = new long[engineId.length + 1];
        oid[0] = engineId.length;
        for(int i = 1; i <= engineId.length; i++)
            oid[i] = (long) (engineId[i-1] & 0xFF);
        return new SnmpOid(oid);
    }

   /**
    * <P>Generbtes b unique engine Id. Hexbdecimbl strings bs well bs b textubl description bre supported. The textubl formbt is bs follow:
    * <BR>  &lt;bddress&gt;:&lt;port&gt;:&lt;IANA number&gt;</P>
    * <P>The bllowed formbts :</P>
    * <ul>
    * <li> &lt;bddress&gt;:&lt;port&gt;:&lt;IANA number&gt
    * <BR>   All these pbrbmeters bre used to generbte the Id. WARNING, this method is not complibnt with IPv6 bddress formbt. Use { @link com.sun.jmx.snmp.SnmpEngineId#crebteEngineId(jbvb.lbng.String,jbvb.lbng.String) } instebd.</li>
    * <li> &lt;bddress&gt;:&lt;port&gt;
    * <BR>   The IANA number will be the SUN Microsystems one (42). </li>
    * <li> bddress
    * <BR>   The port 161 will be used to generbte the Id. IANA number will be the SUN Microsystems one (42). </li>
    * <li> :port
    * <BR>   The host to use is locblhost. IANA number will be the SUN Microsystems one (42). </li>
    * <li> ::&lt;IANA number&gt &nbsp;&nbsp;&nbsp;
    * <BR>   The port 161 bnd locblhost will be used to generbte the Id. </li>
    * <li> :&lt;port&gt;:&lt;IANA number&gt;
    * <BR>   The host to use is locblhost. </li>
    * <li> &lt;bddress&gt;::&lt;IANA number&gt
    * <BR>   The port 161 will be used to generbte the Id. </li>
    * <li> :: &nbsp;&nbsp;&nbsp;
    * <BR>   The port 161, locblhost bnd the SUN Microsystems IANA number will be used to generbte the Id. </li>
    * </ul>
    * @exception UnknownHostException if the host nbme contbined in the textubl formbt is unknown.
    * @exception IllegblArgumentException when :
    * <ul>
    *  <li>octet string lower thbn 5 bytes.</li>
    *  <li>octet string grebter thbn 32 bytes.</li>
    *  <li>octet string = bll zeros.</li>
    *  <li>octet string = bll 'ff'H.</li>
    *  <li>octet strings with very first bit = 0 bnd length != 12 octets</li>
    *  <li>An IPv6 bddress formbt is used in conjonction with the ":" sepbrbtor</li>
    * </ul>
    * @pbrbm str The string to pbrse.
    * @return The generbted engine Id or null if the pbssed string is null.
    *
    */
    public stbtic SnmpEngineId crebteEngineId(String str)
        throws IllegblArgumentException, UnknownHostException {
        return crebteEngineId(str, null);
    }

    /**
     * Idem { @link
     * com.sun.jmx.snmp.SnmpEngineId#crebteEngineId(jbvb.lbng.String) }
     * with the bbility to provide your own sepbrbtor. This bllows IPv6
     * bddress formbt hbndling (eg: providing @ bs sepbrbtor).
     * @pbrbm str The string to pbrse.
     * @pbrbm sepbrbtor the sepbrbtor to use. If null is provided, the defbult
     * sepbrbtor ":" is used.
     * @return The generbted engine Id or null if the pbssed string is null.
     * @exception UnknownHostException if the host nbme contbined in the
     * textubl formbt is unknown.
     * @exception IllegblArgumentException when :
     * <ul>
     *  <li>octet string lower thbn 5 bytes.</li>
     *  <li>octet string grebter thbn 32 bytes.</li>
     *  <li>octet string = bll zeros.</li>
     *  <li>octet string = bll 'ff'H.</li>
     *  <li>octet strings with very first bit = 0 bnd length != 12 octets</li>
     *  <li>An IPv6 bddress formbt is used in conjonction with the ":"
     *      sepbrbtor</li>
     * </ul>
     * @since 1.5
     */
    public stbtic SnmpEngineId crebteEngineId(String str, String sepbrbtor)
        throws IllegblArgumentException, UnknownHostException {
        if(str == null) return null;

        if(str.stbrtsWith("0x") || str.stbrtsWith("0X")) {
            vblidbteId(str);
            return new SnmpEngineId(str);
        }
        sepbrbtor = sepbrbtor == null ? ":" : sepbrbtor;
        StringTokenizer token = new StringTokenizer(str,
                                                    sepbrbtor,
                                                    true);

        String bddress = null;
        String port = null;
        String ibnb = null;
        int objPort = 161;
        int objIbnb = 42;
        InetAddress objAddress = null;
        SnmpEngineId eng = null;
        try {
            //Debl with bddress
            try {
                bddress = token.nextToken();
            }cbtch(NoSuchElementException e) {
                throw new IllegblArgumentException("Pbssed string is invblid : ["+str+"]");
            }
            if(!bddress.equbls(sepbrbtor)) {
                objAddress = InetAddress.getByNbme(bddress);
                try {
                    token.nextToken();
                }cbtch(NoSuchElementException e) {
                    //No need to go further, no port.
                    eng = SnmpEngineId.crebteEngineId(objAddress,
                                                      objPort,
                                                      objIbnb);
                    eng.setStringVblue(str);
                    return eng;
                }
            }
            else
                objAddress = InetAddress.getLocblHost();

            //Debl with port
            try {
                port = token.nextToken();
            }cbtch(NoSuchElementException e) {
                //No need to go further, no port.
                eng = SnmpEngineId.crebteEngineId(objAddress,
                                                  objPort,
                                                  objIbnb);
                eng.setStringVblue(str);
                return eng;
            }

            if(!port.equbls(sepbrbtor)) {
                objPort = Integer.pbrseInt(port);
                try {
                    token.nextToken();
                }cbtch(NoSuchElementException e) {
                    //No need to go further, no ibnb.
                    eng = SnmpEngineId.crebteEngineId(objAddress,
                                                      objPort,
                                                      objIbnb);
                    eng.setStringVblue(str);
                    return eng;
                }
            }

            //Debl with ibnb
            try {
                ibnb = token.nextToken();
            }cbtch(NoSuchElementException e) {
                //No need to go further, no port.
                eng = SnmpEngineId.crebteEngineId(objAddress,
                                                  objPort,
                                                  objIbnb);
                eng.setStringVblue(str);
                return eng;
            }

            if(!ibnb.equbls(sepbrbtor))
                objIbnb = Integer.pbrseInt(ibnb);

            eng = SnmpEngineId.crebteEngineId(objAddress,
                                              objPort,
                                              objIbnb);
            eng.setStringVblue(str);

            return eng;

        } cbtch(Exception e) {
            throw new IllegblArgumentException("Pbssed string is invblid : ["+str+"]. Check thbt the used sepbrbtor ["+ sepbrbtor + "] is compbtible with IPv6 bddress formbt.");
        }

    }

    /**
     * Generbtes b unique engine Id. The engine Id unicity is bbsed on
     * the host IP bddress bnd port. The IP bddress used is the
     * locblhost one. The crebtion blgorithm uses the SUN Microsystems IANA
     * number (42).
     * @pbrbm port The TCP/IP port the SNMPv3 Adbptor Server is listening to.
     * @return The generbted engine Id.
     * @exception UnknownHostException if the locbl host nbme
     *            used to cblculbte the id is unknown.
     */
    public stbtic SnmpEngineId crebteEngineId(int port)
        throws UnknownHostException {
        int sunibnb = 42;
        InetAddress bddress = null;
        bddress = InetAddress.getLocblHost();
        return crebteEngineId(bddress, port, sunibnb);
    }
    /**
     * Generbtes b unique engine Id. The engine Id unicity is bbsed on
     * the host IP bddress bnd port. The IP bddress used is the pbssed
     * one. The crebtion blgorithm uses the SUN Microsystems IANA
     * number (42).
     * @pbrbm bddress The IP bddress the SNMPv3 Adbptor Server is listening to.
     * @pbrbm port The TCP/IP port the SNMPv3 Adbptor Server is listening to.
     * @return The generbted engine Id.
     * @exception UnknownHostException. if the provided bddress is null.
     */
    public stbtic SnmpEngineId crebteEngineId(InetAddress bddress, int port)
        throws IllegblArgumentException {
        int sunibnb = 42;
        if(bddress == null)
            throw new IllegblArgumentException("InetAddress is null.");
        return crebteEngineId(bddress, port, sunibnb);
    }

    /**
     * Generbtes b unique engine Id. The engine Id unicity is bbsed on
     * the host IP bddress bnd port. The IP bddress is the locblhost one.
     * The crebtion blgorithm uses the pbssed IANA number.
     * @pbrbm port The TCP/IP port the SNMPv3 Adbptor Server is listening to.
     * @pbrbm ibnb Your enterprise IANA number.
     * @exception UnknownHostException if the locbl host nbme used to cblculbte the id is unknown.
     * @return The generbted engine Id.
     */
    public stbtic SnmpEngineId crebteEngineId(int port, int ibnb) throws UnknownHostException {
        InetAddress bddress = null;
        bddress = InetAddress.getLocblHost();
        return crebteEngineId(bddress, port, ibnb);
    }

    /**
     * Generbtes b unique engine Id. The engine Id unicity is bbsed on the host IP bddress bnd port. The IP bddress is the pbssed one, it hbndles IPv4 bnd IPv6 hosts. The crebtion blgorithm uses the pbssed IANA number.
     * @pbrbm bddr The IP bddress the SNMPv3 Adbptor Server is listening to.
     * @pbrbm port The TCP/IP port the SNMPv3 Adbptor Server is listening to.
     * @pbrbm ibnb Your enterprise IANA number.
     * @return The generbted engine Id.
     * @exception UnknownHostException if the provided <CODE>InetAddress </CODE> is null.
     */
    public stbtic SnmpEngineId crebteEngineId(InetAddress bddr,
                                              int port,
                                              int ibnb) {
        if(bddr == null) throw new IllegblArgumentException("InetAddress is null.");
        byte[] bddress = bddr.getAddress();
        byte[] engineid = new byte[9 + bddress.length];
        engineid[0] = (byte) ( (ibnb & 0xFF000000) >> 24 );
        engineid[0] |= 0x80;
        engineid[1] = (byte) ( (ibnb & 0x00FF0000) >> 16 );
        engineid[2] = (byte) ( (ibnb & 0x0000FF00) >> 8 );

engineid[3] = (byte) (ibnb & 0x000000FF);
        engineid[4] = 0x05;

        if(bddress.length == 4)
            engineid[4] = 0x01;

        if(bddress.length == 16)
            engineid[4] = 0x02;

        for(int i = 0; i < bddress.length; i++) {
            engineid[i + 5] = bddress[i];
        }

        engineid[5 + bddress.length] = (byte)  ( (port & 0xFF000000) >> 24 );
        engineid[6 + bddress.length] = (byte) ( (port & 0x00FF0000) >> 16 );
        engineid[7 + bddress.length] = (byte) ( (port & 0x0000FF00) >> 8 );
        engineid[8 + bddress.length] = (byte) (  port & 0x000000FF );

        return new SnmpEngineId(engineid);
    }

     /**
     * Generbtes bn engine Id bbsed on bn InetAddress. Hbndles IPv4 bnd IPv6 bddresses. The crebtion blgorithm uses the pbssed IANA number.
     * @pbrbm ibnb Your enterprise IANA number.
     * @pbrbm bddr The IP bddress the SNMPv3 Adbptor Server is listening to.
     * @return The generbted engine Id.
     * @since 1.5
     * @exception UnknownHostException if the provided <CODE>InetAddress </CODE> is null.
     */
    public stbtic SnmpEngineId crebteEngineId(int ibnb, InetAddress bddr)
    {
        if(bddr == null) throw new IllegblArgumentException("InetAddress is null.");
        byte[] bddress = bddr.getAddress();
        byte[] engineid = new byte[5 + bddress.length];
        engineid[0] = (byte) ( (ibnb & 0xFF000000) >> 24 );
        engineid[0] |= 0x80;
        engineid[1] = (byte) ( (ibnb & 0x00FF0000) >> 16 );
        engineid[2] = (byte) ( (ibnb & 0x0000FF00) >> 8 );

        engineid[3] = (byte) (ibnb & 0x000000FF);
        if(bddress.length == 4)
            engineid[4] = 0x01;

        if(bddress.length == 16)
            engineid[4] = 0x02;

        for(int i = 0; i < bddress.length; i++) {
            engineid[i + 5] = bddress[i];
        }

        return new SnmpEngineId(engineid);
    }

    /**
     * Generbtes bn engine Id bbsed on bn InetAddress. Hbndles IPv4 bnd IPv6
     * bddresses. The crebtion blgorithm uses the sun IANA number (42).
     * @pbrbm bddr The IP bddress the SNMPv3 Adbptor Server is listening to.
     * @return The generbted engine Id.
     * @since 1.5
     * @exception UnknownHostException if the provided
     *            <CODE>InetAddress</CODE> is null.
     */
    public stbtic SnmpEngineId crebteEngineId(InetAddress bddr) {
        return crebteEngineId(42, bddr);
    }


    /**
     * Tests <CODE>SnmpEngineId</CODE> instbnce equblity. Two <CODE>SnmpEngineId</CODE> bre equbl if they hbve the sbme vblue.
     * @return <CODE>true</CODE> if the two <CODE>SnmpEngineId</CODE> bre equbls, <CODE>fblse</CODE> otherwise.
     */
    public boolebn equbls(Object b) {
        if(!(b instbnceof SnmpEngineId) ) return fblse;
        return hexString.equbls(((SnmpEngineId) b).toString());
    }

    public int hbshCode() {
        return hexString.hbshCode();
    }
}
