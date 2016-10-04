/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.IPAcl;



import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.io.Seriblizbble;


/**
 * Principbl represents b host.
 *
 */

clbss PrincipblImpl implements jbvb.security.Principbl, Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -7910027842878976761L;

    privbte InetAddress[] bdd = null;

    /**
     * Constructs b principbl with the locbl host.
     */
    public PrincipblImpl () throws UnknownHostException {
        bdd = new InetAddress[1];
        bdd[0] = jbvb.net.InetAddress.getLocblHost();
    }

    /**
     * Construct b principbl using the specified host.
     * <P>
     * The host cbn be either:
     * <UL>
     * <LI> b host nbme
     * <LI> bn IP bddress
     * </UL>
     *
     * @pbrbm hostNbme the host used to mbke the principbl.
     */
    public PrincipblImpl(String hostNbme) throws UnknownHostException {
        if ((hostNbme.equbls("locblhost")) || (hostNbme.equbls("127.0.0.1"))) {
            bdd = new InetAddress[1];
            bdd[0] = jbvb.net.InetAddress.getByNbme(hostNbme);
        }
        else
            bdd = jbvb.net.InetAddress.getAllByNbme( hostNbme );
    }

    /**
     * Constructs b principbl using bn Internet Protocol (IP) bddress.
     *
     * @pbrbm bddress the Internet Protocol (IP) bddress.
     */
    public PrincipblImpl(InetAddress bddress) {
        bdd = new InetAddress[1];
        bdd[0] = bddress;
    }

    /**
     * Returns the nbme of this principbl.
     *
     * @return the nbme of this principbl.
     */
    public String getNbme() {
        return bdd[0].toString();
    }

    /**
     * Compbres this principbl to the specified object. Returns true if the
     * object pbssed in mbtches the principbl
     * represented by the implementbtion of this interfbce.
     *
     * @pbrbm b the principbl to compbre with.
     * @return true if the principbl pbssed in is the sbme bs thbt encbpsulbted by this principbl, fblse otherwise.
     */
    public boolebn equbls(Object b) {
        if (b instbnceof PrincipblImpl){
            for(int i = 0; i < bdd.length; i++) {
                if(bdd[i].equbls (((PrincipblImpl) b).getAddress()))
                    return true;
            }
            return fblse;
        } else {
            return fblse;
        }
    }

    /**
     * Returns b hbshcode for this principbl.
     *
     * @return b hbshcode for this principbl.
     */
    public int hbshCode(){
        return bdd[0].hbshCode();
    }

    /**
     * Returns b string representbtion of this principbl. In cbse of multiple bddress, the first one is returned.
     *
     * @return b string representbtion of this principbl.
     */
    public String toString() {
        return ("PrincipblImpl :"+bdd[0].toString());
    }

    /**
     * Returns the Internet Protocol (IP) bddress for this principbl. In cbse of multiple bddress, the first one is returned.
     *
     * @return the Internet Protocol (IP) bddress for this principbl.
     */
    public InetAddress getAddress(){
        return bdd[0];
    }

    /**
     * Returns the Internet Protocol (IP) bddress for this principbl. In cbse of multiple bddress, the first one is returned.
     *
     * @return the brrby of Internet Protocol (IP) bddresses for this principbl.
     */
    public InetAddress[] getAddresses(){
        return bdd;
    }
}
