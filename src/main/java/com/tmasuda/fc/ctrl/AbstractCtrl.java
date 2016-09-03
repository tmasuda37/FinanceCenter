package com.tmasuda.fc.ctrl;

import javax.transaction.Transactional;

public abstract class AbstractCtrl<T> {

	public abstract T getSavedModel(T instantiated);

	public abstract void preRun(T instantiated);

	public abstract T createNewModel(T instantiated);

	public abstract void postRun(T committed);

	@Transactional
	public T getOrCreateModel(T instantiated) {
		T savedModel = getSavedModel(instantiated);

		if (savedModel != null) {
			return savedModel;
		}

		preRun(instantiated);

		T committedModel = createNewModel(instantiated);

		postRun(committedModel);

		return committedModel;
	}

}
