import { videoJs } from './videojs';

class CustomVideoJsComponent {
  private static _instance: CustomVideoJsComponent = new CustomVideoJsComponent();
  constructor() {
    if (CustomVideoJsComponent._instance) {
      throw new Error('Singleton class. Cannot instantiate.');
    }
    CustomVideoJsComponent._instance = this;
  }

  public static get instance(): CustomVideoJsComponent {
    return this._instance;
  }
}

export default CustomVideoJsComponent.instance;
