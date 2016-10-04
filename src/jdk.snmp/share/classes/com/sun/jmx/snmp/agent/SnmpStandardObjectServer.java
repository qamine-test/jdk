/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp.bgent;

// jbvb imports
//
import jbvb.io.Seriblizbble;
import jbvb.util.Enumerbtion;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;

// SNMP Runtime imports
//

/**
 * <p>
 * This clbss is b utility clbss thbt trbnsform SNMP GET / SET requests
 * into series of get<i>AttributeNbme</i>() set<i>AttributeNbme</i>()
 * invoked on the MBebn.
 * </p>
 *
 * <p>
 * The trbnsformbtion relies on the metbdbtb informbtion provided by the
 * {@link com.sun.jmx.snmp.bgent.SnmpStbndbrdMetbServer} object which is
 * pbssed bs first pbrbmeter to every method. This SnmpStbndbrdMetbServer
 * object is usublly b Metbdbtb object generbted by <code>mibgen</code>.
 * </p>
 *
 * <p>
 * The MBebn is not invoked directly by this clbss but through the
 * metbdbtb object which holds b reference on it.
 * </p>
 *
 * <p><b><i>
 * This clbss is used internblly by mibgen generbted metbdbtb objects bnd
 * you should never need to use it directly.
 * </b></i></p>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/

public clbss SnmpStbndbrdObjectServer implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -4641068116505308488L;

    /**
     * Generic hbndling of the <CODE>get</CODE> operbtion.
     * <p> The defbult implementbtion of this method is to loop over the
     * vbrbind list bssocibted with the sub-request bnd to cbll
     * <CODE>get(vbr.oid.getOidArc(depth), dbtb);</CODE>
     * <pre>
     * public void get(SnmpStbndbrdMetbServer metb, SnmpMibSubRequest req,
     *                 int depth)
     *    throws SnmpStbtusException {
     *
     *    finbl Object dbtb = req.getUserDbtb();
     *
     *    for (Enumerbtion e= req.getElements(); e.hbsMoreElements();) {
     *
     *        finbl SnmpVbrBind vbr= (SnmpVbrBind) e.nextElement();
     *
     *        try {
     *            // This method will generbte b SnmpStbtusException
     *            // if `depth' is out of bounds.
     *            //
     *            finbl long id = vbr.oid.getOidArc(depth);
     *            vbr.vblue = metb.get(id, dbtb);
     *        } cbtch(SnmpStbtusException x) {
     *            req.registerGetException(vbr,x);
     *        }
     *    }
     * }
     * </pre>
     * <p> You cbn override this method if you need to implement some
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources.
     * <p>
     *
     * @pbrbm metb  A pointer to the generbted metb-dbtb object which
     *              implements the <code>SnmpStbndbrdMetbServer</code>
     *              interfbce.
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException An error occurred while bccessing
     *  the MIB node.
     */
    public void get(SnmpStbndbrdMetbServer metb, SnmpMibSubRequest req,
                    int depth)
        throws SnmpStbtusException {

        finbl Object dbtb = req.getUserDbtb();

        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements();) {
            finbl SnmpVbrBind vbr= e.nextElement();
            try {
                finbl long id = vbr.oid.getOidArc(depth);
                vbr.vblue = metb.get(id, dbtb);
            } cbtch(SnmpStbtusException x) {
                req.registerGetException(vbr,x);
            }
        }
    }

    /**
     * Generic hbndling of the <CODE>set</CODE> operbtion.
     * <p> The defbult implementbtion of this method is to loop over the
     * vbrbind list bssocibted with the sub-request bnd to cbll
     * <CODE>set(vbr.vblue, vbr.oid.getOidArc(depth), dbtb);</CODE>
     * <pre>
     * public void set(SnmpStbndbrdMetbServer metb, SnmpMibSubRequest req,
     *                 int depth)
     *    throws SnmpStbtusException {
     *
     *    finbl Object dbtb = req.getUserDbtb();
     *
     *    for (Enumerbtion e= req.getElements(); e.hbsMoreElements();) {
     *
     *        finbl SnmpVbrBind vbr= (SnmpVbrBind) e.nextElement();
     *
     *        try {
     *            // This method will generbte b SnmpStbtusException
     *            // if `depth' is out of bounds.
     *            //
     *            finbl long id = vbr.oid.getOidArc(depth);
     *            vbr.vblue = metb.set(vbr.vblue, id, dbtb);
     *        } cbtch(SnmpStbtusException x) {
     *            req.registerSetException(vbr,x);
     *        }
     *    }
     * }
     * </pre>
     * <p> You cbn override this method if you need to implement some
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources.
     * <p>
     *
     * @pbrbm metb  A pointer to the generbted metb-dbtb object which
     *              implements the <code>SnmpStbndbrdMetbServer</code>
     *              interfbce.
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException An error occurred while bccessing
     *  the MIB node.
     */
    public void set(SnmpStbndbrdMetbServer metb, SnmpMibSubRequest req,
                    int depth)
        throws SnmpStbtusException {

        finbl Object dbtb = req.getUserDbtb();

        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements();) {
            SnmpVbrBind vbr = e.nextElement();
            try {
                // This method will generbte b SnmpStbtusException
                // if `depth' is out of bounds.
                //
                finbl long id = vbr.oid.getOidArc(depth);
                vbr.vblue = metb.set(vbr.vblue, id, dbtb);
            } cbtch(SnmpStbtusException x) {
                req.registerSetException(vbr,x);
            }
        }
    }

    /**
     * Generic hbndling of the <CODE>check</CODE> operbtion.
     * <p> The defbult implementbtion of this method is to loop over the
     * vbrbind list bssocibted with the sub-request bnd to cbll
     * <CODE>check(vbr.vblue, vbr.oid.getOidArc(depth), dbtb);</CODE>
     * <pre>
     * public void check(SnmpStbndbrdMetbServer metb, SnmpMibSubRequest req,
     *                   int depth)
     *    throws SnmpStbtusException {
     *
     *    finbl Object dbtb = req.getUserDbtb();
     *
     *    for (Enumerbtion e= req.getElements(); e.hbsMoreElements();) {
     *
     *        finbl SnmpVbrBind vbr= (SnmpVbrBind) e.nextElement();
     *
     *        try {
     *            // This method will generbte b SnmpStbtusException
     *            // if `depth' is out of bounds.
     *            //
     *            finbl long id = vbr.oid.getOidArc(depth);
     *            metb.check(vbr.vblue, id, dbtb);
     *        } cbtch(SnmpStbtusException x) {
     *            req.registerCheckException(vbr,x);
     *        }
     *    }
     * }
     * </pre>
     * <p> You cbn override this method if you need to implement some
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources, or if you need to implement some consistency
     * checks between the different vblues provided in the vbrbind list.
     * <p>
     *
     * @pbrbm metb  A pointer to the generbted metb-dbtb object which
     *              implements the <code>SnmpStbndbrdMetbServer</code>
     *              interfbce.
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException An error occurred while bccessing
     *  the MIB node.
     */
    public void check(SnmpStbndbrdMetbServer metb, SnmpMibSubRequest req,
                      int depth)
        throws SnmpStbtusException {

        finbl Object dbtb = req.getUserDbtb();

        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements();) {
            finbl SnmpVbrBind vbr = e.nextElement();
            try {
                // This method will generbte b SnmpStbtusException
                // if `depth' is out of bounds.
                //
                finbl long id = vbr.oid.getOidArc(depth);
                metb.check(vbr.vblue,id,dbtb);
            } cbtch(SnmpStbtusException x) {
                req.registerCheckException(vbr,x);
            }
        }
    }
}
