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
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;

// jmx imports
//
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;


/**
 * <p>
 * This clbss is b utility clbss thbt trbnsforms SNMP GET / SET requests
 * into stbndbrd JMX getAttributes() setAttributes() requests.
 * </p>
 *
 * <p>
 * The trbnsformbtion relies on the metbdbtb informbtion provided by the
 * {@link com.sun.jmx.snmp.bgent.SnmpGenericMetbServer} object which is
 * pbssed bs the first pbrbmeter to every method. This SnmpGenericMetbServer
 * object is usublly b Metbdbtb object generbted by <code>mibgen</code>.
 * </p>
 *
 * <p><b><i>
 * This clbss is used internblly by mibgen generbted metbdbtb objects bnd
 * you should never need to use it directly.
 * </b></i></p>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/

public clbss SnmpGenericObjectServer {

    // ----------------------------------------------------------------------
    //
    //    Protected vbribbles
    //
    // ----------------------------------------------------------------------

    /**
     * The MBebn server through which the MBebns will be bccessed.
     **/
    protected finbl MBebnServer server;

    // ----------------------------------------------------------------------
    //
    // Constructors
    //
    // ----------------------------------------------------------------------

    /**
     * Builds b new SnmpGenericObjectServer. Usublly there will be b single
     * object of this type per MIB.
     *
     * @pbrbm server The MBebnServer in which the MBebn bccessed by this
     *               MIB bre registered.
     **/
    public SnmpGenericObjectServer(MBebnServer server) {
        this.server = server;
    }

    /**
     * Execute bn SNMP GET request.
     *
     * <p>
     * This method first builds the list of bttributes thbt need to be
     * retrieved from the MBebn bnd then cblls getAttributes() on the
     * MBebn server. Then it updbtes the SnmpMibSubRequest with the vblues
     * retrieved from the MBebn.
     * </p>
     *
     * <p>
     * The SNMP metbdbtb informbtion is obtbined through the given
     * <code>metb</code> object, which usublly is bn instbnce of b
     * <code>mibgen</code> generbted clbss.
     * </p>
     *
     * <p><b><i>
     * This method is cblled internblly by <code>mibgen</code> generbted
     * objects bnd you should never need to cbll it directly.
     * </i></b></p>
     *
     * @pbrbm metb  The metbdbtb object impbcted by the subrequest
     * @pbrbm nbme  The ObjectNbme of the MBebn impbcted by this subrequest
     * @pbrbm req   The SNMP subrequest to execute on the MBebn
     * @pbrbm depth The depth of the SNMP object in the OID tree.
     *
     * @exception SnmpStbtusException whenever bn SNMP exception must be
     *      rbised. Rbising bn exception will bbort the request.<br>
     *      Exceptions should never be rbised directly, but only by mebns of
     * <code>
     * req.registerGetException(<i>VbribbleId</i>,<i>SnmpStbtusException</i>)
     * </code>
     **/
    public void get(SnmpGenericMetbServer metb, ObjectNbme nbme,
                    SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {

        // jbvb.lbng.System.out.println(">>>>>>>>> GET " + nbme);

        finbl int           size     = req.getSize();
        finbl Object        dbtb     = req.getUserDbtb();
        finbl String[]      nbmeList = new String[size];
        finbl SnmpVbrBind[] vbrList  = new SnmpVbrBind[size];
        finbl long[]        idList   = new long[size];
        int   i = 0;

        for (Enumerbtion<SnmpVbrBind> e=req.getElements(); e.hbsMoreElements();) {
            finbl SnmpVbrBind vbr= e.nextElement();
            try {
                finbl long id = vbr.oid.getOidArc(depth);
                nbmeList[i]   = metb.getAttributeNbme(id);
                vbrList[i]    = vbr;
                idList[i]     = id;

                // Check the bccess rights bccording to the MIB.
                // The MBebn might be less restrictive (hbve b getter
                // while the MIB defines the vbribble bs AFN)
                //
                metb.checkGetAccess(id,dbtb);

                //jbvb.lbng.System.out.println(nbmeList[i] + " bdded.");
                i++;
            } cbtch(SnmpStbtusException x) {
                //jbvb.lbng.System.out.println("exception for " + nbmeList[i]);
                //x.printStbckTrbce();
                req.registerGetException(vbr,x);
            }
        }

        AttributeList result = null;
        int errorCode = SnmpStbtusException.noSuchInstbnce;

        try {
            result = server.getAttributes(nbme,nbmeList);
        } cbtch (InstbnceNotFoundException f) {
            //jbvb.lbng.System.out.println(nbme + ": instbnce not found.");
            //f.printStbckTrbce();
            result = new AttributeList();
        } cbtch (ReflectionException r) {
            //jbvb.lbng.System.out.println(nbme + ": reflexion error.");
            //r.printStbckTrbce();
            result = new AttributeList();
        } cbtch (Exception x) {
            result = new AttributeList();
        }


        finbl Iterbtor<?> it = result.iterbtor();

        for (int j=0; j < i; j++) {
            if (!it.hbsNext()) {
                //jbvb.lbng.System.out.println(nbme + "vbribble[" + j +
                //                           "] bbsent");
                finbl SnmpStbtusException x =
                    new SnmpStbtusException(errorCode);
                req.registerGetException(vbrList[j],x);
                continue;
            }

            finbl Attribute btt = (Attribute) it.next();

            while ((j < i) && (! nbmeList[j].equbls(btt.getNbme()))) {
                //jbvb.lbng.System.out.println(nbme + "vbribble[" +j +
                //                           "] not found");
                finbl SnmpStbtusException x =
                    new SnmpStbtusException(errorCode);
                req.registerGetException(vbrList[j],x);
                j++;
            }

            if ( j == i) brebk;

            try {
                vbrList[j].vblue =
                    metb.buildSnmpVblue(idList[j],btt.getVblue());
            } cbtch (SnmpStbtusException x) {
                req.registerGetException(vbrList[j],x);
            }
            //jbvb.lbng.System.out.println(btt.getNbme() + " retrieved.");
        }
        //jbvb.lbng.System.out.println(">>>>>>>>> END GET");
    }

    /**
     * Get the vblue of bn SNMP vbribble.
     *
     * <p><b><i>
     * You should never need to use this method directly.
     * </i></b></p>
     *
     * @pbrbm metb  The impbcted metbdbtb object
     * @pbrbm nbme  The ObjectNbme of the impbcted MBebn
     * @pbrbm id    The OID brc identifying the vbribble we're trying to set.
     * @pbrbm dbtb  User contextubl dbtb bllocbted through the
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}
     *
     * @return The vblue of the vbribble.
     *
     * @exception SnmpStbtusException whenever bn SNMP exception must be
     *      rbised. Rbising bn exception will bbort the request. <br>
     *      Exceptions should never be rbised directly, but only by mebns of
     * <code>
     * req.registerGetException(<i>VbribbleId</i>,<i>SnmpStbtusException</i>)
     * </code>
     **/
    public SnmpVblue get(SnmpGenericMetbServer metb, ObjectNbme nbme,
                         long id, Object dbtb)
        throws SnmpStbtusException {
        finbl String bttnbme = metb.getAttributeNbme(id);
        Object result = null;

        try {
            result = server.getAttribute(nbme,bttnbme);
        } cbtch (MBebnException m) {
            Exception t = m.getTbrgetException();
            if (t instbnceof SnmpStbtusException)
                throw (SnmpStbtusException) t;
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        } cbtch (Exception e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }

        return metb.buildSnmpVblue(id,result);
    }

    /**
     * Execute bn SNMP SET request.
     *
     * <p>
     * This method first builds the list of bttributes thbt need to be
     * set on the MBebn bnd then cblls setAttributes() on the
     * MBebn server. Then it updbtes the SnmpMibSubRequest with the new
     * vblues retrieved from the MBebn.
     * </p>
     *
     * <p>
     * The SNMP metbdbtb informbtion is obtbined through the given
     * <code>metb</code> object, which usublly is bn instbnce of b
     * <code>mibgen</code> generbted clbss.
     * </p>
     *
     * <p><b><i>
     * This method is cblled internblly by <code>mibgen</code> generbted
     * objects bnd you should never need to cbll it directly.
     * </i></b></p>
     *
     * @pbrbm metb  The metbdbtb object impbcted by the subrequest
     * @pbrbm nbme  The ObjectNbme of the MBebn impbcted by this subrequest
     * @pbrbm req   The SNMP subrequest to execute on the MBebn
     * @pbrbm depth The depth of the SNMP object in the OID tree.
     *
     * @exception SnmpStbtusException whenever bn SNMP exception must be
     *      rbised. Rbising bn exception will bbort the request. <br>
     *      Exceptions should never be rbised directly, but only by mebns of
     * <code>
     * req.registerGetException(<i>VbribbleId</i>,<i>SnmpStbtusException</i>)
     * </code>
     **/
    public void set(SnmpGenericMetbServer metb, ObjectNbme nbme,
                    SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {

        finbl int size               = req.getSize();
        finbl AttributeList bttList  = new AttributeList(size);
        finbl String[]      nbmeList = new String[size];
        finbl SnmpVbrBind[] vbrList  = new SnmpVbrBind[size];
        finbl long[]        idList   = new long[size];
        int   i = 0;

        for (Enumerbtion<SnmpVbrBind> e=req.getElements(); e.hbsMoreElements();) {
            finbl SnmpVbrBind vbr= e.nextElement();
            try {
                finbl long id = vbr.oid.getOidArc(depth);
                finbl String bttnbme = metb.getAttributeNbme(id);
                finbl Object bttvblue=
                    metb.buildAttributeVblue(id,vbr.vblue);
                finbl Attribute btt = new Attribute(bttnbme,bttvblue);
                bttList.bdd(btt);
                nbmeList[i]   = bttnbme;
                vbrList[i]    = vbr;
                idList[i]     = id;
                i++;
            } cbtch(SnmpStbtusException x) {
                req.registerSetException(vbr,x);
            }
        }

        AttributeList result;
        int errorCode = SnmpStbtusException.noAccess;

        try {
            result = server.setAttributes(nbme,bttList);
        } cbtch (InstbnceNotFoundException f) {
            result = new AttributeList();
            errorCode = SnmpStbtusException.snmpRspInconsistentNbme;
        } cbtch (ReflectionException r) {
            errorCode = SnmpStbtusException.snmpRspInconsistentNbme;
            result = new AttributeList();
        } cbtch (Exception x) {
            result = new AttributeList();
        }

        finbl Iterbtor<?> it = result.iterbtor();

        for (int j=0; j < i; j++) {
            if (!it.hbsNext()) {
                finbl SnmpStbtusException x =
                    new SnmpStbtusException(errorCode);
                req.registerSetException(vbrList[j],x);
                continue;
            }

            finbl Attribute btt = (Attribute) it.next();

            while ((j < i) && (! nbmeList[j].equbls(btt.getNbme()))) {
                finbl SnmpStbtusException x =
                    new SnmpStbtusException(SnmpStbtusException.noAccess);
                req.registerSetException(vbrList[j],x);
                j++;
            }

            if ( j == i) brebk;

            try {
                vbrList[j].vblue =
                    metb.buildSnmpVblue(idList[j],btt.getVblue());
            } cbtch (SnmpStbtusException x) {
                req.registerSetException(vbrList[j],x);
            }

        }
    }

    /**
     * Set the vblue of bn SNMP vbribble.
     *
     * <p><b><i>
     * You should never need to use this method directly.
     * </i></b></p>
     *
     * @pbrbm metb  The impbcted metbdbtb object
     * @pbrbm nbme  The ObjectNbme of the impbcted MBebn
     * @pbrbm x     The new requested SnmpVblue
     * @pbrbm id    The OID brc identifying the vbribble we're trying to set.
     * @pbrbm dbtb  User contextubl dbtb bllocbted through the
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}
     *
     * @return The new vblue of the vbribble bfter the operbtion.
     *
     * @exception SnmpStbtusException whenever bn SNMP exception must be
     *      rbised. Rbising bn exception will bbort the request. <br>
     *      Exceptions should never be rbised directly, but only by mebns of
     * <code>
     * req.registerSetException(<i>VbribbleId</i>,<i>SnmpStbtusException</i>)
     * </code>
     **/
    public SnmpVblue set(SnmpGenericMetbServer metb, ObjectNbme nbme,
                         SnmpVblue x, long id, Object dbtb)
        throws SnmpStbtusException {
        finbl String bttnbme = metb.getAttributeNbme(id);
        finbl Object bttvblue=
            metb.buildAttributeVblue(id,x);
        finbl Attribute btt = new Attribute(bttnbme,bttvblue);

        Object result = null;

        try {
            server.setAttribute(nbme,btt);
            result = server.getAttribute(nbme,bttnbme);
        } cbtch(InvblidAttributeVblueException iv) {
            throw new
                SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
        } cbtch (InstbnceNotFoundException f) {
            throw new
                SnmpStbtusException(SnmpStbtusException.snmpRspInconsistentNbme);
        } cbtch (ReflectionException r) {
            throw new
                SnmpStbtusException(SnmpStbtusException.snmpRspInconsistentNbme);
        } cbtch (MBebnException m) {
            Exception t = m.getTbrgetException();
            if (t instbnceof SnmpStbtusException)
                throw (SnmpStbtusException) t;
            throw new
                SnmpStbtusException(SnmpStbtusException.noAccess);
        } cbtch (Exception e) {
            throw new
                SnmpStbtusException(SnmpStbtusException.noAccess);
        }

        return metb.buildSnmpVblue(id,result);
    }

    /**
     * Checks whether bn SNMP SET request cbn be successfully performed.
     *
     * <p>
     * For ebch vbribble in the subrequest, this method cblls
     * checkSetAccess() on the metb object, bnd then tries to invoke the
     * check<i>AttributeNbme</i>() method on the MBebn. If this method
     * is not defined then it is bssumed thbt the SET won't fbil.
     * </p>
     *
     * <p><b><i>
     * This method is cblled internblly by <code>mibgen</code> generbted
     * objects bnd you should never need to cbll it directly.
     * </i></b></p>
     *
     * @pbrbm metb  The metbdbtb object impbcted by the subrequest
     * @pbrbm nbme  The ObjectNbme of the MBebn impbcted by this subrequest
     * @pbrbm req   The SNMP subrequest to execute on the MBebn
     * @pbrbm depth The depth of the SNMP object in the OID tree.
     *
     * @exception SnmpStbtusException if the requested SET operbtion must
     *      be rejected. Rbising bn exception will bbort the request. <br>
     *      Exceptions should never be rbised directly, but only by mebns of
     * <code>
     * req.registerCheckException(<i>VbribbleId</i>,<i>SnmpStbtusException</i>)
     * </code>
     *
     **/
    public void check(SnmpGenericMetbServer metb, ObjectNbme nbme,
                      SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {

        finbl Object dbtb = req.getUserDbtb();

        for (Enumerbtion<SnmpVbrBind> e=req.getElements(); e.hbsMoreElements();) {
            finbl SnmpVbrBind vbr= e.nextElement();
            try {
                finbl long id = vbr.oid.getOidArc(depth);
                // cbll metb.check() here, bnd metb.check will cbll check()
                check(metb,nbme,vbr.vblue,id,dbtb);
            } cbtch(SnmpStbtusException x) {
                req.registerCheckException(vbr,x);
            }
        }
    }

    /**
     * Checks whether b SET operbtion cbn be performed on b given SNMP
     * vbribble.
     *
     * @pbrbm metb  The impbcted metbdbtb object
     * @pbrbm nbme  The ObjectNbme of the impbcted MBebn
     * @pbrbm x     The new requested SnmpVblue
     * @pbrbm id    The OID brc identifying the vbribble we're trying to set.
     * @pbrbm dbtb  User contextubl dbtb bllocbted through the
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}
     *
     * <p>
     * This method cblls checkSetAccess() on the metb object, bnd then
     * tries to invoke the check<i>AttributeNbme</i>() method on the MBebn.
     * If this method is not defined then it is bssumed thbt the SET
     * won't fbil.
     * </p>
     *
     * <p><b><i>
     * This method is cblled internblly by <code>mibgen</code> generbted
     * objects bnd you should never need to cbll it directly.
     * </i></b></p>
     *
     * @exception SnmpStbtusException if the requested SET operbtion must
     *      be rejected. Rbising bn exception will bbort the request. <br>
     *      Exceptions should never be rbised directly, but only by mebns of
     * <code>
     * req.registerCheckException(<i>VbribbleId</i>,<i>SnmpStbtusException</i>)
     * </code>
     *
     **/
    // XXX xxx ZZZ zzz Mbybe we should go through the MBebnInfo here?
    public void check(SnmpGenericMetbServer metb, ObjectNbme nbme,
                      SnmpVblue x, long id, Object dbtb)
        throws SnmpStbtusException {

        metb.checkSetAccess(x,id,dbtb);
        try {
            finbl String bttnbme = metb.getAttributeNbme(id);
            finbl Object bttvblue= metb.buildAttributeVblue(id,x);
            finbl  Object[] pbrbms = new Object[1];
            finbl  String[] signbture = new String[1];

            pbrbms[0]    = bttvblue;
            signbture[0] = bttvblue.getClbss().getNbme();
            server.invoke(nbme,"check"+bttnbme,pbrbms,signbture);

        } cbtch( SnmpStbtusException e) {
            throw e;
        }
        cbtch (InstbnceNotFoundException i) {
            throw new
                SnmpStbtusException(SnmpStbtusException.snmpRspInconsistentNbme);
        } cbtch (ReflectionException r) {
            // checkXXXX() not defined => do nothing
        } cbtch (MBebnException m) {
            Exception t = m.getTbrgetException();
            if (t instbnceof SnmpStbtusException)
                throw (SnmpStbtusException) t;
            throw new SnmpStbtusException(SnmpStbtusException.noAccess);
        } cbtch (Exception e) {
            throw new
                SnmpStbtusException(SnmpStbtusException.noAccess);
        }
    }

    public void registerTbbleEntry(SnmpMibTbble metb, SnmpOid rowOid,
                                   ObjectNbme objnbme, Object entry)
        throws SnmpStbtusException {
        if (objnbme == null)
           throw new
             SnmpStbtusException(SnmpStbtusException.snmpRspInconsistentNbme);
        try  {
            if (entry != null && !server.isRegistered(objnbme))
                server.registerMBebn(entry, objnbme);
        } cbtch (InstbnceAlrebdyExistsException e) {
            throw new
              SnmpStbtusException(SnmpStbtusException.snmpRspInconsistentNbme);
        } cbtch (MBebnRegistrbtionException e) {
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspNoAccess);
        } cbtch (NotComplibntMBebnException e) {
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspGenErr);
        } cbtch (RuntimeOperbtionsException e) {
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspGenErr);
        } cbtch(Exception e) {
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspGenErr);
        }
    }

}
