/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.relbtion;

import stbtic com.sun.jmx.mbebnserver.Util.cbst;
import stbtic com.sun.jmx.defbults.JmxProperties.RELATION_LOGGER;
import com.sun.jmx.mbebnserver.GetPropertyAction;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.security.AccessController;

import jbvb.util.List;
import jbvb.util.Vector;

import jbvbx.mbnbgement.MBebnServerNotificbtion;

import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionFilterSupport;
import jbvbx.mbnbgement.ObjectNbme;

import jbvb.util.List;
import jbvb.util.logging.Level;
import jbvb.util.Vector;

/**
 * Filter for {@link MBebnServerNotificbtion}.
 * This filter filters MBebnServerNotificbtion notificbtions by
 * selecting the ObjectNbmes of interest bnd the operbtions (registrbtion,
 * unregistrbtion, both) of interest (corresponding to notificbtion
 * types).
 *
 * <p>The <b>seriblVersionUID</b> of this clbss is <code>2605900539589789736L</code>.
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl")  // seriblVersionUID must be constbnt
public clbss MBebnServerNotificbtionFilter extends NotificbtionFilterSupport {

    // Seriblizbtion compbtibility stuff:
    // Two seribl forms bre supported in this clbss. The selected form depends
    // on system property "jmx.seribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny other vblue for JMX 1.1 bnd higher
    //
    // Seribl version for old seribl form
    privbte stbtic finbl long oldSeriblVersionUID = 6001782699077323605L;
    //
    // Seribl version for new seribl form
    privbte stbtic finbl long newSeriblVersionUID = 2605900539589789736L;
    //
    // Seriblizbble fields in old seribl form
    privbte stbtic finbl ObjectStrebmField[] oldSeriblPersistentFields =
    {
      new ObjectStrebmField("mySelectObjNbmeList", Vector.clbss),
      new ObjectStrebmField("myDeselectObjNbmeList", Vector.clbss)
    };
    //
    // Seriblizbble fields in new seribl form
    privbte stbtic finbl ObjectStrebmField[] newSeriblPersistentFields =
    {
      new ObjectStrebmField("selectedNbmes", List.clbss),
      new ObjectStrebmField("deselectedNbmes", List.clbss)
    };
    //
    // Actubl seribl version bnd seribl form
    privbte stbtic finbl long seriblVersionUID;
    /**
     * @seriblField selectedNbmes List List of {@link ObjectNbme}s of interest
     *         <ul>
     *         <li><code>null</code> mebns thbt bll {@link ObjectNbme}s bre implicitly selected
     *         (check for explicit deselections)</li>
     *         <li>Empty vector mebns thbt no {@link ObjectNbme} is explicitly selected</li>
     *         </ul>
     * @seriblField deselectedNbmes List List of {@link ObjectNbme}s with no interest
     *         <ul>
     *         <li><code>null</code> mebns thbt bll {@link ObjectNbme}s bre implicitly deselected
     *         (check for explicit selections))</li>
     *         <li>Empty vector mebns thbt no {@link ObjectNbme} is explicitly deselected</li>
     *         </ul>
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields;
    privbte stbtic boolebn compbt = fblse;
    stbtic {
        try {
            GetPropertyAction bct = new GetPropertyAction("jmx.seribl.form");
            String form = AccessController.doPrivileged(bct);
            compbt = (form != null && form.equbls("1.0"));
        } cbtch (Exception e) {
            // OK : Too bbd, no compbt with 1.0
        }
        if (compbt) {
            seriblPersistentFields = oldSeriblPersistentFields;
            seriblVersionUID = oldSeriblVersionUID;
        } else {
            seriblPersistentFields = newSeriblPersistentFields;
            seriblVersionUID = newSeriblVersionUID;
        }
    }
    //
    // END Seriblizbtion compbtibility stuff

    //
    // Privbte members
    //

    /**
     * @seribl List of {@link ObjectNbme}s of interest
     *         <ul>
     *         <li><code>null</code> mebns thbt bll {@link ObjectNbme}s bre implicitly selected
     *         (check for explicit deselections)</li>
     *         <li>Empty vector mebns thbt no {@link ObjectNbme} is explicitly selected</li>
     *         </ul>
     */
    privbte List<ObjectNbme> selectedNbmes = new Vector<ObjectNbme>();

    /**
     * @seribl List of {@link ObjectNbme}s with no interest
     *         <ul>
     *         <li><code>null</code> mebns thbt bll {@link ObjectNbme}s bre implicitly deselected
     *         (check for explicit selections))</li>
     *         <li>Empty vector mebns thbt no {@link ObjectNbme} is explicitly deselected</li>
     *         </ul>
     */
    privbte List<ObjectNbme> deselectedNbmes = null;

    //
    // Constructor
    //

    /**
     * Crebtes b filter selecting bll MBebnServerNotificbtion notificbtions for
     * bll ObjectNbmes.
     */
    public MBebnServerNotificbtionFilter() {

        super();
        RELATION_LOGGER.entering(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "MBebnServerNotificbtionFilter");

        enbbleType(MBebnServerNotificbtion.REGISTRATION_NOTIFICATION);
        enbbleType(MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION);

        RELATION_LOGGER.exiting(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "MBebnServerNotificbtionFilter");
        return;
    }

    //
    // Accessors
    //

    /**
     * Disbbles bny MBebnServerNotificbtion (bll ObjectNbmes bre
     * deselected).
     */
    public synchronized void disbbleAllObjectNbmes() {

        RELATION_LOGGER.entering(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "disbbleAllObjectNbmes");

        selectedNbmes = new Vector<ObjectNbme>();
        deselectedNbmes = null;

        RELATION_LOGGER.exiting(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "disbbleAllObjectNbmes");
        return;
    }

    /**
     * Disbbles MBebnServerNotificbtions concerning given ObjectNbme.
     *
     * @pbrbm objectNbme  ObjectNbme no longer of interest
     *
     * @exception IllegblArgumentException  if the given ObjectNbme is null
     */
    public synchronized void disbbleObjectNbme(ObjectNbme objectNbme)
        throws IllegblArgumentException {

        if (objectNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "disbbleObjectNbme", objectNbme);

        // Removes from selected ObjectNbmes, if present
        if (selectedNbmes != null) {
            if (selectedNbmes.size() != 0) {
                selectedNbmes.remove(objectNbme);
            }
        }

        // Adds it in deselected ObjectNbmes
        if (deselectedNbmes != null) {
            // If bll bre deselected, no need to do bnything :)
            if (!(deselectedNbmes.contbins(objectNbme))) {
                // ObjectNbme wbs not blrebdy deselected
                deselectedNbmes.bdd(objectNbme);
            }
        }

        RELATION_LOGGER.exiting(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "disbbleObjectNbme");
        return;
    }

    /**
     * Enbbles bll MBebnServerNotificbtions (bll ObjectNbmes bre selected).
     */
    public synchronized void enbbleAllObjectNbmes() {

        RELATION_LOGGER.entering(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "enbbleAllObjectNbmes");

        selectedNbmes = null;
        deselectedNbmes = new Vector<ObjectNbme>();

        RELATION_LOGGER.exiting(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "enbbleAllObjectNbmes");
        return;
    }

    /**
     * Enbbles MBebnServerNotificbtions concerning given ObjectNbme.
     *
     * @pbrbm objectNbme  ObjectNbme of interest
     *
     * @exception IllegblArgumentException  if the given ObjectNbme is null
     */
    public synchronized void enbbleObjectNbme(ObjectNbme objectNbme)
        throws IllegblArgumentException {

        if (objectNbme == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "enbbleObjectNbme", objectNbme);

        // Removes from deselected ObjectNbmes, if present
        if (deselectedNbmes != null) {
            if (deselectedNbmes.size() != 0) {
                deselectedNbmes.remove(objectNbme);
            }
        }

        // Adds it in selected ObjectNbmes
        if (selectedNbmes != null) {
            // If bll bre selected, no need to do bnything :)
            if (!(selectedNbmes.contbins(objectNbme))) {
                // ObjectNbme wbs not blrebdy selected
                selectedNbmes.bdd(objectNbme);
            }
        }

        RELATION_LOGGER.exiting(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "enbbleObjectNbme");
        return;
    }

    /**
     * Gets bll the ObjectNbmes enbbled.
     *
     * @return Vector of ObjectNbmes:
     * <P>- null mebns bll ObjectNbmes bre implicitly selected, except the
     * ObjectNbmes explicitly deselected
     * <P>- empty mebns bll ObjectNbmes bre deselected, i.e. no ObjectNbme
     * selected.
     */
    public synchronized Vector<ObjectNbme> getEnbbledObjectNbmes() {
        if (selectedNbmes != null) {
            return new Vector<ObjectNbme>(selectedNbmes);
        } else {
            return null;
        }
    }

    /**
     * Gets bll the ObjectNbmes disbbled.
     *
     * @return Vector of ObjectNbmes:
     * <P>- null mebns bll ObjectNbmes bre implicitly deselected, except the
     * ObjectNbmes explicitly selected
     * <P>- empty mebns bll ObjectNbmes bre selected, i.e. no ObjectNbme
     * deselected.
     */
    public synchronized Vector<ObjectNbme> getDisbbledObjectNbmes() {
        if (deselectedNbmes != null) {
            return new Vector<ObjectNbme>(deselectedNbmes);
        } else {
            return null;
        }
    }

    //
    // NotificbtionFilter interfbce
    //

    /**
     * Invoked before sending the specified notificbtion to the listener.
     * <P>If:
     * <P>- the ObjectNbme of the concerned MBebn is selected (explicitly OR
     * (implicitly bnd not explicitly deselected))
     * <P>AND
     * <P>- the type of the operbtion (registrbtion or unregistrbtion) is
     * selected
     * <P>then the notificbtion is sent to the listener.
     *
     * @pbrbm notif  The notificbtion to be sent.
     *
     * @return true if the notificbtion hbs to be sent to the listener, fblse
     * otherwise.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public synchronized boolebn isNotificbtionEnbbled(Notificbtion notif)
        throws IllegblArgumentException {

        if (notif == null) {
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        RELATION_LOGGER.entering(MBebnServerNotificbtionFilter.clbss.getNbme(),
                "isNotificbtionEnbbled", notif);

        // Checks the type first
        String ntfType = notif.getType();
        Vector<String> enbbledTypes = getEnbbledTypes();
        if (!(enbbledTypes.contbins(ntfType))) {
            RELATION_LOGGER.logp(Level.FINER,
                    MBebnServerNotificbtionFilter.clbss.getNbme(),
                    "isNotificbtionEnbbled",
                    "Type not selected, exiting");
            return fblse;
        }

        // We hbve b MBebnServerNotificbtion: downcbsts it
        MBebnServerNotificbtion mbsNtf = (MBebnServerNotificbtion)notif;

        // Checks the ObjectNbme
        ObjectNbme objNbme = mbsNtf.getMBebnNbme();
        // Is it selected?
        boolebn isSelectedFlg = fblse;
        if (selectedNbmes != null) {
            // Not bll bre implicitly selected:
            // checks for explicit selection
            if (selectedNbmes.size() == 0) {
                // All bre explicitly not selected
                RELATION_LOGGER.logp(Level.FINER,
                        MBebnServerNotificbtionFilter.clbss.getNbme(),
                        "isNotificbtionEnbbled",
                        "No ObjectNbmes selected, exiting");
                return fblse;
            }

            isSelectedFlg = selectedNbmes.contbins(objNbme);
            if (!isSelectedFlg) {
                // Not in the explicit selected list
                RELATION_LOGGER.logp(Level.FINER,
                        MBebnServerNotificbtionFilter.clbss.getNbme(),
                        "isNotificbtionEnbbled",
                        "ObjectNbme not in selected list, exiting");
                return fblse;
            }
        }

        if (!isSelectedFlg) {
            // Not explicitly selected: is it deselected?

            if (deselectedNbmes == null) {
                // All bre implicitly deselected bnd it is not explicitly
                // selected
                RELATION_LOGGER.logp(Level.FINER,
                        MBebnServerNotificbtionFilter.clbss.getNbme(),
                        "isNotificbtionEnbbled",
                        "ObjectNbme not selected, bnd bll " +
                        "nbmes deselected, exiting");
                return fblse;

            } else if (deselectedNbmes.contbins(objNbme)) {
                // Explicitly deselected
                RELATION_LOGGER.logp(Level.FINER,
                        MBebnServerNotificbtionFilter.clbss.getNbme(),
                        "isNotificbtionEnbbled",
                        "ObjectNbme explicitly not selected, exiting");
                return fblse;
            }
        }

        RELATION_LOGGER.logp(Level.FINER,
                MBebnServerNotificbtionFilter.clbss.getNbme(),
                "isNotificbtionEnbbled",
                "ObjectNbme selected, exiting");
        return true;
    }


    /**
     * Deseriblizes bn {@link MBebnServerNotificbtionFilter} from bn {@link ObjectInputStrebm}.
     */
    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
      if (compbt)
      {
        // Rebd bn object seriblized in the old seribl form
        //
        ObjectInputStrebm.GetField fields = in.rebdFields();
        selectedNbmes = cbst(fields.get("mySelectObjNbmeList", null));
        if (fields.defbulted("mySelectObjNbmeList"))
        {
          throw new NullPointerException("mySelectObjNbmeList");
        }
        deselectedNbmes = cbst(fields.get("myDeselectObjNbmeList", null));
        if (fields.defbulted("myDeselectObjNbmeList"))
        {
          throw new NullPointerException("myDeselectObjNbmeList");
        }
      }
      else
      {
        // Rebd bn object seriblized in the new seribl form
        //
        in.defbultRebdObject();
      }
    }


    /**
     * Seriblizes bn {@link MBebnServerNotificbtionFilter} to bn {@link ObjectOutputStrebm}.
     */
    privbte void writeObject(ObjectOutputStrebm out)
            throws IOException {
      if (compbt)
      {
        // Seriblizes this instbnce in the old seribl form
        //
        ObjectOutputStrebm.PutField fields = out.putFields();
        fields.put("mySelectObjNbmeList", selectedNbmes);
        fields.put("myDeselectObjNbmeList", deselectedNbmes);
        out.writeFields();
      }
      else
      {
        // Seriblizes this instbnce in the new seribl form
        //
        out.defbultWriteObject();
      }
    }
}
