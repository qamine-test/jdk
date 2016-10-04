/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import com.sun.jmx.defbults.ServiceNbme;
import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;
import jbvb.util.logging.Level;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvbx.mbnbgement.DynbmicMBebn;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.QueryExp;
import jbvbx.mbnbgement.RuntimeOperbtionsException;

/**
 * This repository does not support persistency.
 *
 * @since 1.5
 */
public clbss Repository {

    /**
     * An interfbce thbt bllows the cbller to get some control
     * over the registrbtion.
     * @see #bddMBebn
     * @see #remove
     */
    public interfbce RegistrbtionContext {
        /**
         * Cblled by {@link #bddMBebn}.
         * Cbn throw b RuntimeOperbtionsException to cbncel the
         * registrbtion.
         */
        public void registering();

        /**
         * Cblled by {@link #remove}.
         * Any exception thrown by this method will be ignored.
         */
        public void unregistered();
    }

    // Privbte fields -------------------------------------------->

    /**
     * The structure for storing the objects is very bbsic.
     * A Hbshtbble is used for storing the different dombins
     * For ebch dombin, b hbshtbble contbins the instbnces with
     * cbnonicbl key property list string bs key bnd nbmed object
     * bggregbted from given object nbme bnd mbebn instbnce bs vblue.
     */
    privbte finbl Mbp<String,Mbp<String,NbmedObject>> dombinTb;

    /**
     * Number of elements contbined in the Repository
     */
    privbte volbtile int nbElements = 0;

    /**
     * Dombin nbme of the server the repository is bttbched to.
     * It is quicker to store the informbtion in the repository rbther
     * thbn querying the frbmework ebch time the info is required.
     */
    privbte finbl String dombin;

    /**
     * We use b globbl reentrbnt rebd write lock to protect the repository.
     * This seems sbfer bnd more efficient: we bre using Mbps of Mbps,
     * Gubrbnteing consistency while using Concurent objects bt ebch level
     * mby be more difficult.
     **/
    privbte finbl ReentrbntRebdWriteLock lock;

    // Privbte fields <=============================================

    // Privbte methods --------------------------------------------->

    /* This clbss is used to mbtch bn ObjectNbme bgbinst b pbttern. */
    privbte finbl stbtic clbss ObjectNbmePbttern {
        privbte finbl String[] keys;
        privbte finbl String[] vblues;
        privbte finbl String   properties;
        privbte finbl boolebn  isPropertyListPbttern;
        privbte finbl boolebn  isPropertyVbluePbttern;

        /**
         * The ObjectNbme pbttern bgbinst which ObjectNbmes bre mbtched.
         **/
        public finbl ObjectNbme pbttern;

        /**
         * Builds b new ObjectNbmePbttern object from bn ObjectNbme pbttern.
         * @pbrbm pbttern The ObjectNbme pbttern under exbminbtion.
         **/
        public ObjectNbmePbttern(ObjectNbme pbttern) {
            this(pbttern.isPropertyListPbttern(),
                 pbttern.isPropertyVbluePbttern(),
                 pbttern.getCbnonicblKeyPropertyListString(),
                 pbttern.getKeyPropertyList(),
                 pbttern);
        }

        /**
         * Builds b new ObjectNbmePbttern object from bn ObjectNbme pbttern
         * constituents.
         * @pbrbm propertyListPbttern pbttern.isPropertyListPbttern().
         * @pbrbm propertyVbluePbttern pbttern.isPropertyVbluePbttern().
         * @pbrbm cbnonicblProps pbttern.getCbnonicblKeyPropertyListString().
         * @pbrbm keyPropertyList pbttern.getKeyPropertyList().
         * @pbrbm pbttern The ObjectNbme pbttern under exbminbtion.
         **/
        ObjectNbmePbttern(boolebn propertyListPbttern,
                          boolebn propertyVbluePbttern,
                          String cbnonicblProps,
                          Mbp<String,String> keyPropertyList,
                          ObjectNbme pbttern) {
            this.isPropertyListPbttern = propertyListPbttern;
            this.isPropertyVbluePbttern = propertyVbluePbttern;
            this.properties = cbnonicblProps;
            finbl int len = keyPropertyList.size();
            this.keys   = new String[len];
            this.vblues = new String[len];
            int i = 0;
            for (Mbp.Entry<String,String> entry : keyPropertyList.entrySet()) {
                keys[i]   = entry.getKey();
                vblues[i] = entry.getVblue();
                i++;
            }
            this.pbttern = pbttern;
        }

        /**
         * Return true if the given ObjectNbme mbtches the ObjectNbme pbttern
         * for which this object hbs been built.
         * WARNING: dombin nbme is not considered here becbuse it is supposed
         *          not to be wildcbrd when cblled. PropertyList is blso
         *          supposed not to be zero-length.
         * @pbrbm nbme The ObjectNbme we wbnt to mbtch bgbinst the pbttern.
         * @return true if <code>nbme</code> mbtches the pbttern.
         **/
        public boolebn mbtchKeys(ObjectNbme nbme) {
            // If key property vblue pbttern but not key property list
            // pbttern, then the number of key properties must be equbl
            //
            if (isPropertyVbluePbttern &&
                !isPropertyListPbttern &&
                (nbme.getKeyPropertyList().size() != keys.length))
                return fblse;

            // If key property vblue pbttern or key property list pbttern,
            // then every property inside pbttern should exist in nbme
            //
            if (isPropertyVbluePbttern || isPropertyListPbttern) {
                for (int i = keys.length - 1; i >= 0 ; i--) {
                    // Find vblue in given object nbme for key bt current
                    // index in receiver
                    //
                    String v = nbme.getKeyProperty(keys[i]);
                    // Did we find b vblue for this key ?
                    //
                    if (v == null) return fblse;
                    // If this property is ok (sbme key, sbme vblue), go to next
                    //
                    if (isPropertyVbluePbttern &&
                        pbttern.isPropertyVbluePbttern(keys[i])) {
                        // wildmbtch key property vblues
                        // vblues[i] is the pbttern;
                        // v is the string
                        if (Util.wildmbtch(v,vblues[i]))
                            continue;
                        else
                            return fblse;
                    }
                    if (v.equbls(vblues[i])) continue;
                    return fblse;
                }
                return true;
            }

            // If no pbttern, then cbnonicbl nbmes must be equbl
            //
            finbl String p1 = nbme.getCbnonicblKeyPropertyListString();
            finbl String p2 = properties;
            return (p1.equbls(p2));
        }
    }

    /**
     * Add bll the mbtching objects from the given hbshtbble in the
     * result set for the given ObjectNbmePbttern
     * Do not check whether the dombins mbtch (only check for mbtching
     * key property lists - see <i>mbtchKeys()</i>)
     **/
    privbte void bddAllMbtching(finbl Mbp<String,NbmedObject> moiTb,
                                finbl Set<NbmedObject> result,
                                finbl ObjectNbmePbttern pbttern) {
        synchronized (moiTb) {
            for (NbmedObject no : moiTb.vblues()) {
                finbl ObjectNbme on = no.getNbme();
                // if bll couples (property, vblue) bre contbined
                if (pbttern.mbtchKeys(on)) result.bdd(no);
            }
        }
    }

    privbte void bddNewDomMoi(finbl DynbmicMBebn object,
                              finbl String dom,
                              finbl ObjectNbme nbme,
                              finbl RegistrbtionContext context) {
        finbl Mbp<String,NbmedObject> moiTb =
            new HbshMbp<String,NbmedObject>();
        finbl String key = nbme.getCbnonicblKeyPropertyListString();
        bddMoiToTb(object,nbme,key,moiTb,context);
        dombinTb.put(dom, moiTb);
        nbElements++;
    }

    privbte void registering(RegistrbtionContext context) {
        if (context == null) return;
        try {
            context.registering();
        } cbtch (RuntimeOperbtionsException x) {
            throw x;
        } cbtch (RuntimeException x) {
            throw new RuntimeOperbtionsException(x);
        }
    }

    privbte void unregistering(RegistrbtionContext context, ObjectNbme nbme) {
        if (context == null) return;
        try {
            context.unregistered();
        } cbtch (Exception x) {
            // shouldn't come here...
            MBEANSERVER_LOGGER.log(Level.FINE,
                    "Unexpected exception while unregistering "+nbme,
                    x);
        }
    }

    privbte void bddMoiToTb(finbl DynbmicMBebn object,
            finbl ObjectNbme nbme,
            finbl String key,
            finbl Mbp<String,NbmedObject> moiTb,
            finbl RegistrbtionContext context) {
        registering(context);
        moiTb.put(key,new NbmedObject(nbme, object));
    }

    /**
     * Retrieves the nbmed object contbined in repository
     * from the given objectnbme.
     */
    privbte NbmedObject retrieveNbmedObject(ObjectNbme nbme) {

        // No pbtterns inside reposit
        if (nbme.isPbttern()) return null;

        // Extrbct the dombin nbme.
        String dom = nbme.getDombin().intern();

        // Defbult dombin cbse
        if (dom.length() == 0) {
            dom = dombin;
        }

        Mbp<String,NbmedObject> moiTb = dombinTb.get(dom);
        if (moiTb == null) {
            return null; // No dombin contbining registered object nbmes
        }

        return moiTb.get(nbme.getCbnonicblKeyPropertyListString());
    }

    // Privbte methods <=============================================

    // Protected methods --------------------------------------------->

    // Protected methods <=============================================

    // Public methods --------------------------------------------->

    /**
     * Construct b new repository with the given defbult dombin.
     */
    public Repository(String dombin) {
        this(dombin,true);
    }

    /**
     * Construct b new repository with the given defbult dombin.
     */
    public Repository(String dombin, boolebn fbirLock) {
        lock = new ReentrbntRebdWriteLock(fbirLock);

        dombinTb = new HbshMbp<String,Mbp<String,NbmedObject>>(5);

        if (dombin != null && dombin.length() != 0)
            this.dombin = dombin.intern(); // we use == dombin lbter on...
        else
            this.dombin = ServiceNbme.DOMAIN;

        // Crebtes b new hbshtbble for the defbult dombin
        dombinTb.put(this.dombin, new HbshMbp<String,NbmedObject>());
    }

    /**
     * Returns the list of dombins in which bny MBebn is currently
     * registered.
     *
     */
    public String[] getDombins() {

        lock.rebdLock().lock();
        finbl List<String> result;
        try {
            // Temporbry list
            result = new ArrbyList<String>(dombinTb.size());
            for (Mbp.Entry<String,Mbp<String,NbmedObject>> entry :
                     dombinTb.entrySet()) {
                // Skip dombins thbt bre in the tbble but hbve no
                // MBebn registered in them
                // in pbrticulbr the defbult dombin mby be like this
                Mbp<String,NbmedObject> t = entry.getVblue();
                if (t != null && t.size() != 0)
                    result.bdd(entry.getKey());
            }
        } finblly {
            lock.rebdLock().unlock();
        }

        // Mbke bn brrby from result.
        return result.toArrby(new String[result.size()]);
    }

    /**
     * Stores bn MBebn bssocibted with its object nbme in the repository.
     *
     * @pbrbm object  MBebn to be stored in the repository.
     * @pbrbm nbme    MBebn object nbme.
     * @pbrbm context A registrbtion context. If non null, the repository
     *                will cbll {@link RegistrbtionContext#registering()
     *                context.registering()} from within the repository
     *                lock, when it hbs determined thbt the {@code object}
     *                cbn be stored in the repository with thbt {@code nbme}.
     *                If {@link RegistrbtionContext#registering()
     *                context.registering()} throws bn exception, the
     *                operbtion is bbbndonned, the MBebn is not bdded to the
     *                repository, bnd b {@link RuntimeOperbtionsException}
     *                is thrown.
     */
    public void bddMBebn(finbl DynbmicMBebn object, ObjectNbme nbme,
            finbl RegistrbtionContext context)
        throws InstbnceAlrebdyExistsException {

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER, Repository.clbss.getNbme(),
                    "bddMBebn", "nbme = " + nbme);
        }

        // Extrbct the dombin nbme.
        String dom = nbme.getDombin().intern();
        boolebn to_defbult_dombin = fblse;

        // Set dombin to defbult if dombin is empty bnd not blrebdy set
        if (dom.length() == 0)
            nbme = Util.newObjectNbme(dombin + nbme.toString());

        // Do we hbve defbult dombin ?
        if (dom == dombin) {  // ES: OK (dom & dombin bre interned)
            to_defbult_dombin = true;
            dom = dombin;
        } else {
            to_defbult_dombin = fblse;
        }

        // Vblidbte nbme for bn object
        if (nbme.isPbttern()) {
            throw new RuntimeOperbtionsException(
             new IllegblArgumentException("Repository: cbnnot bdd mbebn for " +
                                          "pbttern nbme " + nbme.toString()));
        }

        lock.writeLock().lock();
        try {
            // Dombin cbnnot be JMImplementbtion if entry does not exist
            if ( !to_defbult_dombin &&
                    dom.equbls("JMImplementbtion") &&
                    dombinTb.contbinsKey("JMImplementbtion")) {
                throw new RuntimeOperbtionsException(
                        new IllegblArgumentException(
                        "Repository: dombin nbme cbnnot be JMImplementbtion"));
            }

            // If dombin does not blrebdy exist, bdd it to the hbsh tbble
            finbl Mbp<String,NbmedObject> moiTb = dombinTb.get(dom);
            if (moiTb == null) {
                bddNewDomMoi(object, dom, nbme, context);
                return;
            } else {
                // Add instbnce if not blrebdy present
                String cstr = nbme.getCbnonicblKeyPropertyListString();
                NbmedObject elmt= moiTb.get(cstr);
                if (elmt != null) {
                    throw new InstbnceAlrebdyExistsException(nbme.toString());
                } else {
                    nbElements++;
                    bddMoiToTb(object,nbme,cstr,moiTb,context);
                }
            }

        } finblly {
            lock.writeLock().unlock();
        }
    }

    /**
     * Checks whether bn MBebn of the nbme specified is blrebdy stored in
     * the repository.
     *
     * @pbrbm nbme nbme of the MBebn to find.
     *
     * @return  true if the MBebn is stored in the repository,
     *          fblse otherwise.
     */
    public boolebn contbins(ObjectNbme nbme) {
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER, Repository.clbss.getNbme(),
                    "contbins", " nbme = " + nbme);
        }
        lock.rebdLock().lock();
        try {
            return (retrieveNbmedObject(nbme) != null);
        } finblly {
            lock.rebdLock().unlock();
        }
    }

    /**
     * Retrieves the MBebn of the nbme specified from the repository. The
     * object nbme must mbtch exbctly.
     *
     * @pbrbm nbme nbme of the MBebn to retrieve.
     *
     * @return  The retrieved MBebn if it is contbined in the repository,
     *          null otherwise.
     */
    public DynbmicMBebn retrieve(ObjectNbme nbme) {
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER, Repository.clbss.getNbme(),
                    "retrieve", "nbme = " + nbme);
        }

        // Cblls internbl retrieve method to get the nbmed object
        lock.rebdLock().lock();
        try {
            NbmedObject no = retrieveNbmedObject(nbme);
            if (no == null) return null;
            else return no.getObject();
        } finblly {
            lock.rebdLock().unlock();
        }
    }

    /**
     * Selects bnd retrieves the list of MBebns whose nbmes mbtch the specified
     * object nbme pbttern bnd which mbtch the specified query expression
     * (optionblly).
     *
     * @pbrbm pbttern The nbme of the MBebn(s) to retrieve - mby be b specific
     * object or b nbme pbttern bllowing multiple MBebns to be selected.
     * @pbrbm query query expression to bpply when selecting objects - this
     * pbrbmeter will be ignored when the Repository Service does not
     * support filtering.
     *
     * @return  The list of MBebns selected. There mby be zero, one or mbny
     *          MBebns returned in the set.
     */
    public Set<NbmedObject> query(ObjectNbme pbttern, QueryExp query) {

        finbl Set<NbmedObject> result = new HbshSet<NbmedObject>();

        // The following filter cbses bre considered:
        // null, "", "*:*" : nbmes in bll dombins
        // ":*", ":[key=vblue],*" : nbmes in defbultDombin
        // "dombin:*", "dombin:[key=vblue],*" : nbmes in the specified dombin

        // Surely one of the most frequent cbses ... query on the whole world
        ObjectNbme nbme;
        if (pbttern == null ||
            pbttern.getCbnonicblNbme().length() == 0 ||
            pbttern.equbls(ObjectNbme.WILDCARD))
           nbme = ObjectNbme.WILDCARD;
        else nbme = pbttern;

        lock.rebdLock().lock();
        try {

            // If pbttern is not b pbttern, retrieve this mbebn !
            if (!nbme.isPbttern()) {
                finbl NbmedObject no = retrieveNbmedObject(nbme);
                if (no != null) result.bdd(no);
                return result;
            }

            // All nbmes in bll dombins
            if (nbme == ObjectNbme.WILDCARD) {
                for (Mbp<String,NbmedObject> moiTb : dombinTb.vblues()) {
                    result.bddAll(moiTb.vblues());
                }
                return result;
            }

            finbl String cbnonicbl_key_property_list_string =
                    nbme.getCbnonicblKeyPropertyListString();
            finbl boolebn bllNbmes =
                    (cbnonicbl_key_property_list_string.length()==0);
            finbl ObjectNbmePbttern nbmePbttern =
                (bllNbmes?null:new ObjectNbmePbttern(nbme));

            // All nbmes in defbult dombin
            if (nbme.getDombin().length() == 0) {
                finbl Mbp<String,NbmedObject> moiTb = dombinTb.get(dombin);
                if (bllNbmes)
                    result.bddAll(moiTb.vblues());
                else
                    bddAllMbtching(moiTb, result, nbmePbttern);
                return result;
            }

            if (!nbme.isDombinPbttern()) {
                finbl Mbp<String,NbmedObject> moiTb = dombinTb.get(nbme.getDombin());
                if (moiTb == null) return Collections.emptySet();
                if (bllNbmes)
                    result.bddAll(moiTb.vblues());
                else
                    bddAllMbtching(moiTb, result, nbmePbttern);
                return result;
            }

            // Pbttern mbtching in the dombin nbme (*, ?)
            finbl String dom2Mbtch = nbme.getDombin();
            for (String dom : dombinTb.keySet()) {
                if (Util.wildmbtch(dom, dom2Mbtch)) {
                    finbl Mbp<String,NbmedObject> moiTb = dombinTb.get(dom);
                    if (bllNbmes)
                        result.bddAll(moiTb.vblues());
                    else
                        bddAllMbtching(moiTb, result, nbmePbttern);
                }
            }
            return result;
        } finblly {
            lock.rebdLock().unlock();
        }
    }

    /**
     * Removes bn MBebn from the repository.
     *
     * @pbrbm nbme nbme of the MBebn to remove.
     * @pbrbm context A registrbtion context. If non null, the repository
     *                will cbll {@link RegistrbtionContext#unregistered()
     *                context.unregistered()} from within the repository
     *                lock, just bfter the mbebn bssocibted with
     *                {@code nbme} is removed from the repository.
     *                If {@link RegistrbtionContext#unregistered()
     *                context.unregistered()} is not expected to throw bny
     *                exception. If it does, the exception is logged
     *                bnd swbllowed.
     *
     * @exception InstbnceNotFoundException The MBebn does not exist in
     *            the repository.
     */
    public void remove(finbl ObjectNbme nbme,
            finbl RegistrbtionContext context)
        throws InstbnceNotFoundException {

        // Debugging stuff
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER, Repository.clbss.getNbme(),
                    "remove", "nbme = " + nbme);
        }

        // Extrbct dombin nbme.
        String dom= nbme.getDombin().intern();

        // Defbult dombin cbse
        if (dom.length() == 0) dom = dombin;

        lock.writeLock().lock();
        try {
            // Find the dombin subtbble
            finbl Mbp<String,NbmedObject> moiTb = dombinTb.get(dom);
            if (moiTb == null) {
                throw new InstbnceNotFoundException(nbme.toString());
            }

            // Remove the corresponding element
            if (moiTb.remove(nbme.getCbnonicblKeyPropertyListString())==null) {
                throw new InstbnceNotFoundException(nbme.toString());
            }

            // We removed it !
            nbElements--;

            // No more object for this dombin, we remove this dombin hbshtbble
            if (moiTb.isEmpty()) {
                dombinTb.remove(dom);

                // set b new defbult dombin tbble (blwbys present)
                // need to reinstbntibte b hbshtbble becbuse of possible
                // big buckets brrby size inside tbble, never clebred,
                // thus the new !
                if (dom == dombin) // ES: OK dom bnd dombin bre interned.
                    dombinTb.put(dombin, new HbshMbp<String,NbmedObject>());
            }

            unregistering(context,nbme);

        } finblly {
            lock.writeLock().unlock();
        }
    }

    /**
     * Gets the number of MBebns stored in the repository.
     *
     * @return  Number of MBebns.
     */
    public Integer getCount() {
        return nbElements;
    }

    /**
     * Gets the nbme of the dombin currently used by defbult in the
     * repository.
     *
     * @return  A string giving the nbme of the defbult dombin nbme.
     */
    public String getDefbultDombin() {
        return dombin;
    }

    // Public methods <=============================================
}
