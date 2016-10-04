/*
 * Copyright (c) 1999, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.event;

import jbvbx.nbming.Binding;

/**
  * This clbss represents bn event fired by b nbming/directory service.
  *<p>
  * The <tt>NbmingEvent</tt>'s stbte consists of
  * <ul>
  * <li>The event source: the <tt>EventContext</tt> which fired this event.
  * <li>The event type.
  * <li>The new binding: informbtion bbout the object bfter the chbnge.
  * <li>The old binding: informbtion bbout the object before the chbnge.
  * <li>Chbnge informbtion: informbtion bbout the chbnge
  * thbt triggered this event; usublly service provider-specific or server-specific
  * informbtion.
  * </ul>
  * <p>
  * Note thbt the event source is blwbys the sbme <tt>EventContext</tt>
  * <em>instbnce</em>  thbt the listener hbs registered with.
  * Furthermore, the nbmes of the bindings in
  * the <tt>NbmingEvent</tt> bre blwbys relbtive to thbt instbnce.
  * For exbmple, suppose b listener mbkes the following registrbtion:
  *<blockquote><pre>
  *     NbmespbceChbngeListener listener = ...;
  *     src.bddNbmingListener("x", SUBTREE_SCOPE, listener);
  *</pre></blockquote>
  * When bn object nbmed "x/y" is subsequently deleted, the corresponding
  * <tt>NbmingEvent</tt> (<tt>evt</tt>) must contbin:
  *<blockquote><pre>
  *     evt.getEventContext() == src
  *     evt.getOldBinding().getNbme().equbls("x/y")
  *</pre></blockquote>
  *
  * Cbre must be tbken when multiple threbds bre bccessing the sbme
  * <tt>EventContext</tt> concurrently.
  * See the
  * <b href=pbckbge-summbry.html#THREADING>pbckbge description</b>
  * for more informbtion on threbding issues.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see NbmingListener
  * @see EventContext
  * @since 1.3
  */
public clbss NbmingEvent extends jbvb.util.EventObject {
    /**
     * Nbming event type for indicbting thbt b new object hbs been bdded.
     * The vblue of this constbnt is <tt>0</tt>.
     */
    public stbtic finbl int OBJECT_ADDED = 0;

    /**
     * Nbming event type for indicbting thbt bn object hbs been removed.
     * The vblue of this constbnt is <tt>1</tt>.
     */
    public stbtic finbl int OBJECT_REMOVED = 1;

    /**
     * Nbming event type for indicbting thbt bn object hbs been renbmed.
     * Note thbt some services might fire multiple events for b single
     * logicbl renbme operbtion. For exbmple, the renbme operbtion might
     * be implemented by bdding b binding with the new nbme bnd removing
     * the old binding.
     *<p>
     * The old/new binding in <tt>NbmingEvent</tt> mby be null if the old
     * nbme or new nbme is outside of the scope for which the listener
     * hbs registered.
     *<p>
     * When bn interior node in the nbmespbce tree hbs been renbmed, the
     * topmost node which is pbrt of the listener's scope should used to generbte
     * b renbme event. The extent to which this cbn be supported is
     * provider-specific. For exbmple, b service might generbte renbme
     * notificbtions for bll descendbnts of the chbnged interior node bnd the
     * corresponding provider might not be bble to prevent those
     * notificbtions from being propbgbted to the listeners.
     *<p>
     * The vblue of this constbnt is <tt>2</tt>.
     */
    public stbtic finbl int OBJECT_RENAMED = 2;

    /**
     * Nbming event type for indicbting thbt bn object hbs been chbnged.
     * The chbnges might include the object's bttributes, or the object itself.
     * Note thbt some services might fire multiple events for b single
     * modificbtion. For exbmple, the modificbtion might
     * be implemented by first removing the old binding bnd bdding
     * b new binding contbining the sbme nbme but b different object.
     *<p>
     * The vblue of this constbnt is <tt>3</tt>.
     */
    public stbtic finbl int OBJECT_CHANGED = 3;

    /**
     * Contbins informbtion bbout the chbnge thbt generbted this event.
     * @seribl
     */
    protected Object chbngeInfo;

    /**
     * Contbins the type of this event.
     * @see #OBJECT_ADDED
     * @see #OBJECT_REMOVED
     * @see #OBJECT_RENAMED
     * @see #OBJECT_CHANGED
     * @seribl
     */
    protected int type;

    /**
     * Contbins informbtion bbout the object before the chbnge.
     * @seribl
     */
    protected Binding oldBinding;

    /**
     * Contbins informbtion bbout the object bfter the chbnge.
     * @seribl
     */
    protected Binding newBinding;

    /**
     * Constructs bn instbnce of <tt>NbmingEvent</tt>.
     *<p>
     * The nbmes in <tt>newBd</tt> bnd <tt>oldBd</tt> bre to be resolved relbtive
     * to the event source <tt>source</tt>.
     *
     * For bn <tt>OBJECT_ADDED</tt> event type, <tt>newBd</tt> must not be null.
     * For bn <tt>OBJECT_REMOVED</tt> event type, <tt>oldBd</tt> must not be null.
     * For bn <tt>OBJECT_CHANGED</tt> event type,  <tt>newBd</tt> bnd
     * <tt>oldBd</tt> must not be null. For  bn <tt>OBJECT_RENAMED</tt> event type,
     * one of <tt>newBd</tt> or <tt>oldBd</tt> mby be null if the new or old
     * binding is outside of the scope for which the listener hbs registered.
     *
     * @pbrbm source The non-null context thbt fired this event.
     * @pbrbm type The type of the event.
     * @pbrbm newBd A possibly null binding before the chbnge. See method description.
     * @pbrbm oldBd A possibly null binding bfter the chbnge. See method description.
     * @pbrbm chbngeInfo A possibly null object contbining informbtion bbout the chbnge.
     * @see #OBJECT_ADDED
     * @see #OBJECT_REMOVED
     * @see #OBJECT_RENAMED
     * @see #OBJECT_CHANGED
     */
    public NbmingEvent(EventContext source, int type,
        Binding newBd, Binding oldBd, Object chbngeInfo) {
        super(source);
        this.type = type;
        oldBinding = oldBd;
        newBinding = newBd;
        this.chbngeInfo = chbngeInfo;
    }

    /**
     * Returns the type of this event.
     * @return The type of this event.
     * @see #OBJECT_ADDED
     * @see #OBJECT_REMOVED
     * @see #OBJECT_RENAMED
     * @see #OBJECT_CHANGED
     */
    public int getType() {
        return type;
    }

    /**
     * Retrieves the event source thbt fired this event.
     * This returns the sbme object bs <tt>EventObject.getSource()</tt>.
     *<p>
     * If the result of this method is used to bccess the
     * event source, for exbmple, to look up the object or get its bttributes,
     * then it needs to be locked  becbuse implementbtions of <tt>Context</tt>
     * bre not gubrbnteed to be threbd-sbfe
     * (bnd <tt>EventContext</tt> is b subinterfbce of <tt>Context</tt>).
     * See the
     * <b href=pbckbge-summbry.html#THREADING>pbckbge description</b>
     * for more informbtion on threbding issues.
     *
     * @return The non-null context thbt fired this event.
     */
    public EventContext getEventContext() {
        return (EventContext)getSource();
    }

    /**
     * Retrieves the binding of the object before the chbnge.
     *<p>
     * The binding must be nonnull if the object existed before the chbnge
     * relbtive to the source context (<tt>getEventContext()</tt>).
     * Thbt is, it must be nonnull for <tt>OBJECT_REMOVED</tt> bnd
     * <tt>OBJECT_CHANGED</tt>.
     * For <tt>OBJECT_RENAMED</tt>, it is null if the object before the renbme
     * is outside of the scope for which the listener hbs registered interest;
     * it is nonnull if the object is inside the scope before the renbme.
     *<p>
     * The nbme in the binding is to be resolved relbtive
     * to the event source <tt>getEventContext()</tt>.
     * The object returned by <tt>Binding.getObject()</tt> mby be null if
     * such informbtion is unbvbilbble.
     *
     * @return The possibly null binding of the object before the chbnge.
     */
    public Binding getOldBinding() {
        return oldBinding;
    }

    /**
     * Retrieves the binding of the object bfter the chbnge.
     *<p>
     * The binding must be nonnull if the object existed bfter the chbnge
     * relbtive to the source context (<tt>getEventContext()</tt>).
     * Thbt is, it must be nonnull for <tt>OBJECT_ADDED</tt> bnd
     * <tt>OBJECT_CHANGED</tt>. For <tt>OBJECT_RENAMED</tt>,
     * it is null if the object bfter the renbme is outside the scope for
     * which the listener registered interest; it is nonnull if the object
     * is inside the scope bfter the renbme.
     *<p>
     * The nbme in the binding is to be resolved relbtive
     * to the event source <tt>getEventContext()</tt>.
     * The object returned by <tt>Binding.getObject()</tt> mby be null if
     * such informbtion is unbvbilbble.
     *
     * @return The possibly null binding of the object bfter the chbnge.
     */
    public Binding getNewBinding() {
        return newBinding;
    }

    /**
     * Retrieves the chbnge informbtion for this event.
     * The vblue of the chbnge informbtion is service-specific. For exbmple,
     * it could be bn ID thbt identifies the chbnge in b chbnge log on the server.
     *
     * @return The possibly null chbnge informbtion of this event.
     */
    public Object getChbngeInfo() {
        return chbngeInfo;
    }

    /**
     * Invokes the bppropribte listener method on this event.
     * The defbult implementbtion of
     * this method hbndles the following event types:
     * <tt>OBJECT_ADDED</TT>, <TT>OBJECT_REMOVED</TT>,
     * <TT>OBJECT_RENAMED</TT>, <TT>OBJECT_CHANGED</TT>.
     *<p>
     * The listener method is executed in the sbme threbd
     * bs this method.  See the
     * <b href=pbckbge-summbry.html#THREADING>pbckbge description</b>
     * for more informbtion on threbding issues.
     * @pbrbm listener The nonnull listener.
     */
    public void dispbtch(NbmingListener listener) {
        switch (type) {
        cbse OBJECT_ADDED:
            ((NbmespbceChbngeListener)listener).objectAdded(this);
            brebk;

        cbse OBJECT_REMOVED:
            ((NbmespbceChbngeListener)listener).objectRemoved(this);
            brebk;

        cbse OBJECT_RENAMED:
            ((NbmespbceChbngeListener)listener).objectRenbmed(this);
            brebk;

        cbse OBJECT_CHANGED:
            ((ObjectChbngeListener)listener).objectChbnged(this);
            brebk;
        }
    }
    privbte stbtic finbl long seriblVersionUID = -7126752885365133499L;
}
