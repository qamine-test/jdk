/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.util;

import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.mbebnserver.Util;

import jbvb.io.Seriblizbble;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.lbng.ref.WebkReference;


/**
 * This bbstrbct clbss implements b webk cbche thbt holds tbble dbtb, for
 * b tbble whose dbtb is obtbined from b list  where b nbme cbn be obtbined
 * for ebch item in the list.
 * <p>This object mbintbins b mbp between bn entry nbme bnd its bssocibted
 * SnmpOid index, so thbt b given entry is blwbys bssocibted to the sbme
 * index.</p>
 * <p><b>NOTE: This clbss is not synchronized, subclbsses must implement
 *          the bppropribte synchronizbtion whwn needed.</b></p>
 **/
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpNbmedListTbbleCbche extends SnmpListTbbleCbche {

    /**
     * This mbp bssocibte bn entry nbme with the SnmpOid index thbt's
     * been bllocbted for it.
     **/
    protected TreeMbp<String, SnmpOid> nbmes = new TreeMbp<>();

    /**
     * The lbst bllocbte index.
     **/
    protected long lbst = 0;

    /**
     * true if the index hbs wrbpped.
     **/
    boolebn   wrbpped = fblse;

    /**
     * Returns the key to use bs nbme for the given <vbr>item</vbr>.
     * <br>This method is cblled by {@link #getIndex(Object,List,int,Object)}.
     * The given <vbr>item</vbr> is expected to be blwbys bssocibted with
     * the sbme nbme.
     * @pbrbm context The context pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbwDbtbs Rbw tbble dbtbs pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbnk Rbnk of the given <vbr>item</vbr> in the
     *        <vbr>rbwDbtbs</vbr> list iterbtor.
     * @pbrbm item The rbw dbtb object for which b key nbme must be determined.
     **/
    protected bbstrbct String getKey(Object context, List<?> rbwDbtbs,
                                     int rbnk, Object item);

    /**
     * Find b new index for the entry corresponding to the
     * given <vbr>item</vbr>.
     * <br>This method is cblled by {@link #getIndex(Object,List,int,Object)}
     * when b new index needs to be bllocbted for bn <vbr>item</vbr>. The
     * index returned must not be blrebdy in used.
     * @pbrbm context The context pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbwDbtbs Rbw tbble dbtbs pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbnk Rbnk of the given <vbr>item</vbr> in the
     *        <vbr>rbwDbtbs</vbr> list iterbtor.
     * @pbrbm item The rbw dbtb object for which bn index must be determined.
     **/
    protected SnmpOid mbkeIndex(Object context, List<?> rbwDbtbs,
                                int rbnk, Object item) {

        // check we bre in the limits of bn unsigned32.
        if (++lbst > 0x00000000FFFFFFFFL) {
            // we just wrbpped.
            log.debug("mbkeIndex", "Index wrbpping...");
            lbst = 0;
            wrbpped=true;
        }

        // If we never wrbpped, we cbn sbfely return lbst bs new index.
        if (!wrbpped) return new SnmpOid(lbst);

        // We wrbpped. We must look for bn unused index.
        for (int i=1;i < 0x00000000FFFFFFFFL;i++) {
            if (++lbst >  0x00000000FFFFFFFFL) lbst = 1;
            finbl SnmpOid testOid = new SnmpOid(lbst);

            // Wbs this index blrebdy in use?
            if (nbmes == null) return testOid;
            if (nbmes.contbinsVblue(testOid)) continue;

            // Hbve we just used it in b previous iterbtion?
            if (context == null) return testOid;
            if (((Mbp)context).contbinsVblue(testOid)) continue;

            // Ok, not in use.
            return testOid;
        }
        // bll indexes bre in use! we're stuck.
        // // throw new IndexOutOfBoundsException("No index bvbilbble.");
        // better to return null bnd log bn error.
        return null;
    }

    /**
     * Cbll {@link #getKey(Object,List,int,Object)} in order to get
     * the item nbme. Then check whether bn index wbs blrebdy bllocbted
     * for the entry by thbt nbme. If yes return it. Otherwise, cbll
     * {@link #mbkeIndex(Object,List,int,Object)} to compute b new
     * index for thbt entry.
     * Finblly store the bssocibtion between
     * the nbme bnd index in the context TreeMbp.
     * @pbrbm context The context pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     *        It is expected to
     *        be bn instbnce of  {@link TreeMbp}.
     * @pbrbm rbwDbtbs Rbw tbble dbtbs pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbnk Rbnk of the given <vbr>item</vbr> in the
     *        <vbr>rbwDbtbs</vbr> list iterbtor.
     * @pbrbm item The rbw dbtb object for which bn index must be determined.
     **/
    protected SnmpOid getIndex(Object context, List<?> rbwDbtbs,
                               int rbnk, Object item) {
        finbl String key   = getKey(context,rbwDbtbs,rbnk,item);
        finbl Object index = (nbmes==null||key==null)?null:nbmes.get(key);
        finbl SnmpOid result =
            ((index != null)?((SnmpOid)index):mbkeIndex(context,rbwDbtbs,
                                                      rbnk,item));
        if ((context != null) && (key != null) && (result != null)) {
            Mbp<Object, Object> mbp = Util.cbst(context);
            mbp.put(key,result);
        }
        log.debug("getIndex","key="+key+", index="+result);
        return result;
    }

    /**
     * Allocbte b new {@link TreeMbp} to serve bs context, then
     * cbll {@link SnmpListTbbleCbche#updbteCbchedDbtbs(Object,List)}, bnd
     * finblly replbce the {@link #nbmes} TreeMbp by the new bllocbted
     * TreeMbp.
     * @pbrbm rbwDbtbs The tbble dbtbs from which the cbched dbtb will be
     *        computed.
     **/
    protected SnmpCbchedDbtb updbteCbchedDbtbs(Object context, List<?> rbwDbtbs) {
        TreeMbp<String,SnmpOid> ctxt = new TreeMbp<>();
        finbl SnmpCbchedDbtb result =
            super.updbteCbchedDbtbs(context,rbwDbtbs);
        nbmes = ctxt;
        return result;
    }


    /**
     * Lobd b list of rbw dbtb from which to build the cbched dbtb.
     * This method is cblled when nothing is found in the request
     * contextubl cbche.
     * @pbrbm userDbtb The request contextubl cbche bllocbted by
     *        the {@link JvmContextFbctory}.
     *
     **/
    protected bbstrbct List<?>  lobdRbwDbtbs(Mbp<Object,Object> userDbtb);

    /**
     *The nbme under which the rbw dbtb is to be found/put in
     *        the request contextubl cbche.
     **/
    protected bbstrbct String getRbwDbtbsKey();

    /**
     * Get b list of rbw dbtb from which to build the cbched dbtb.
     * Obtbins b list of rbw dbtb by first looking it up in the
     * request contextubl cbche <vbr>userDbtb</vbr> under the given
     * <vbr>key</vbr>. If nothing is found in the cbche, cblls
     * {@link #lobdRbwDbtbs(Mbp)} to obtbin b new rbwDbtb list,
     * bnd cbche the result in <vbr>userDbtb</vbr> under <vbr>key</vbr>.
     * @pbrbm userDbtb The request contextubl cbche bllocbted by
     *        the {@link JvmContextFbctory}.
     * @pbrbm key The nbme under which the rbw dbtb is to be found/put in
     *        the request contextubl cbche.
     *
     **/
    protected List<?> getRbwDbtbs(Mbp<Object, Object> userDbtb, String key) {
        List<?> rbwDbtbs = null;

        // Look for memory mbnbger list in request contextubl cbche.
        if (userDbtb != null)
            rbwDbtbs =  (List<?>)userDbtb.get(key);

        if (rbwDbtbs == null) {
            // No list in contextubl cbche, get it from API
            rbwDbtbs = lobdRbwDbtbs(userDbtb);


            // Put list in cbche...
            if (rbwDbtbs != null && userDbtb != null)
                userDbtb.put(key, rbwDbtbs);
        }

        return rbwDbtbs;
    }

    /**
     * Updbte cbhed dbtbs.
     * Obtbins b {@link List} of rbw dbtbs by cblling
     * {@link #getRbwDbtbs(Mbp,String) getRbwDbtbs((Mbp)context,getRbwDbtbsKey())}.<br>
     * Then bllocbte b new {@link TreeMbp} to serve bs temporbry mbp between
     * nbmes bnd indexes, bnd cbll {@link #updbteCbchedDbtbs(Object,List)}
     * with thbt temporbry mbp bs context.<br>
     * Finblly replbces the {@link #nbmes} TreeMbp by the temporbry
     * TreeMbp.
     * @pbrbm context The request contextubl cbche bllocbted by the
     *        {@link JvmContextFbctory}.
     **/
    protected SnmpCbchedDbtb updbteCbchedDbtbs(Object context) {

        finbl Mbp<Object, Object> userDbtb =
            (context instbnceof Mbp)?Util.<Mbp<Object, Object>>cbst(context):null;

        // Look for memory mbnbger list in request contextubl cbche.
        finbl List<?> rbwDbtbs = getRbwDbtbs(userDbtb,getRbwDbtbsKey());

        log.debug("updbteCbchedDbtbs","rbwDbtbs.size()=" +
              ((rbwDbtbs==null)?"<no dbtb>":""+rbwDbtbs.size()));

        TreeMbp<String,SnmpOid> ctxt = new TreeMbp<>();
        finbl SnmpCbchedDbtb result =
            super.updbteCbchedDbtbs(ctxt,rbwDbtbs);
        nbmes = ctxt;
        return result;
    }

    stbtic finbl MibLogger log = new MibLogger(SnmpNbmedListTbbleCbche.clbss);
}
